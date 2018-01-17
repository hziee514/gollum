package org.gollum.bank.command;

import org.gollum.core.commanding.BaseCommand;

/**
 * 创建银行账户
 *
 * @author wurenhai
 * @date 2018/1/4
 */
public class CreateAccountCommand extends BaseCommand {

    private final String owner;

    public CreateAccountCommand(String accountId, String owner) {
        super(accountId);
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }
}
