package org.gollum.core.commanding;

/**
 * @author wurenhai
 * @date 2017/12/28
 */
public class ProcessCommandHandler {

    private ICommandHandlerFactory factory;

    public ProcessCommandHandler(ICommandHandlerFactory factory) {
        this.factory = factory;
    }

    public void exec(Command command) {
        //ICommandContext context = new CommandExecuteContext()
        //factory.getExecutor(command.getClass()).exec(context, command);
        //eventService.commit();
    }

}
