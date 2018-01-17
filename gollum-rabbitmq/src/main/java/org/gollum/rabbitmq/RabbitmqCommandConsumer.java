package org.gollum.rabbitmq;

import org.gollum.core.commanding.Command;
import org.gollum.core.commanding.CommandConsumer;
import org.gollum.core.commanding.CommandHandler;

/**
 * @author wurenhai
 * @date 2018/1/17
 */
public class RabbitmqCommandConsumer implements CommandConsumer {

    @Override
    public void register(Class<? extends Command> type, CommandHandler<? extends Command> handler) {

    }

    @Override
    public void consume(Command command) {

    }

}
