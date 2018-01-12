package org.gollum.core.domain;

import org.gollum.common.util.TextUtils;
import org.gollum.core.eventing.DomainEvent;

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
public abstract class BaseAggregateRoot implements AggregateRoot {

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

    protected BaseAggregateRoot() {
        this.changes = new LinkedList<>();
    }

    protected BaseAggregateRoot(String id) {
        this();
        if (TextUtils.isNullOrEmpty(id)) {
            throw new NullPointerException("id");
        }
        this.id = id;
    }

    protected BaseAggregateRoot(String id, int version) {
        this(id);
        if (version < 0) {
            throw new IllegalArgumentException("Version can not negative");
        }
        this.version = version;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public <T> T as(Class<T> type) {
        return (T)this;
    }

    @Override
    public void replayEvents(List<DomainEvent> history) {
        for (DomainEvent e : history) {
            applyChange(e, false);
        }
        if (!history.isEmpty()) {
            this.version = history.get(history.size() - 1).getVersion();
        }
    }

    @Override
    public List<DomainEvent> getChanges() {
        List<DomainEvent> events = new LinkedList<>();
        events.addAll(changes);
        return events;
    }

    /**
     * 清空变更记录
     */
    @Override
    public void acceptChanges() {
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
            this.version++;
            event.setAggregateRootId(this.id);
            event.setVersion(this.version);
            changes.add(event);
        }
    }

}
