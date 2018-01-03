package org.gollum.core.commanding;

/**
 * @author wurenhai
 * @date 2018/1/2
 */
public interface CommandConsumer {

    void register(Class<? extends Command> type, CommandHandler<? extends Command> handler);

    void consume(Command command);

}
