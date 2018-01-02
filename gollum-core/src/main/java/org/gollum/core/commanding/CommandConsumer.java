package org.gollum.core.commanding;

/**
 * @author wurenhai
 * @date 2018/1/2
 */
public interface CommandConsumer {

    void register(Class<? extends AbstractCommand> type, CommandHandler<? extends AbstractCommand> handler);

    void consume(AbstractCommand command);

}
