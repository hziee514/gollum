package org.gollum.bank.domain.account;

import org.gollum.core.eventing.DomainEvent;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class BankAccountCreated extends DomainEvent {

    private String owner;

    public BankAccountCreated(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

}
