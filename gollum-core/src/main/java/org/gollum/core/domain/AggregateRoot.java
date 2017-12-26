package org.gollum.core.domain;

import org.gollum.core.eventing.DomainEvent;
import org.gollum.core.util.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * 聚合根抽象类, 所有聚合根都必须从这里继承
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public abstract class AggregateRoot implements IEventProvider {

    /**
     * 聚合根ID
     */
    protected String id;

    /**
     * 版本号
     */
    protected int version;

    /**
     * 未提交的领域事件
     */
    private final List<DomainEvent> changes;

    protected AggregateRoot() {
        this.changes = new LinkedList<>();
    }

    protected AggregateRoot(String id) {
        this();
        if (TextUtils.isNullOrEmpty(id)) {
            throw new NullPointerException("id");
        }
        this.id = id;
    }

    protected AggregateRoot(String id, int version) {
        this(id);
        if (version < 0) {
            throw new IllegalArgumentException("Version can not negative");
        }
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public <T> T as() {
        return (T)this;
    }

    public void replayEvents(List<DomainEvent> history) {
        for (DomainEvent e : history) {
            applyChange(e, false);
        }
        this.version = history.get(history.size() - 1).getVersion();
    }

    public List<DomainEvent> getUncommittedChanges() {
        return changes;
    }

    public void acceptChanges() {
        this.version = changes.get(changes.size() - 1).getVersion();
        changes.clear();
    }

    protected void applyChange(DomainEvent event) {
        applyChange(event, true);
    }

    private void applyChange(DomainEvent event, boolean isNew) {
        try {
            //自身响应事件改变自身状态, 要求方法名必须为handle, 且不能被混淆
            //fixme: 这样用性能不好，需要优化?
            Method m = getClass().getMethod("handle", event.getClass());
            m.setAccessible(true);
            m.invoke(this, event);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        if (isNew) {
            event.setAggregateRootId(this.id);
            event.setVersion(this.version + 1);
            changes.add(event);
        }
    }

}
