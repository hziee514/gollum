package org.gollum.core.commanding;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class InMemoryCommandBus implements ICommandBus {

    private final ICommandHandlerFactory factory;

    public InMemoryCommandBus(ICommandHandlerFactory factory) {
        this.factory = factory;
    }

    @Override
    public void send(Command command) {
        factory.getExecutor(command.getClass()).exec(command);
    }

}
