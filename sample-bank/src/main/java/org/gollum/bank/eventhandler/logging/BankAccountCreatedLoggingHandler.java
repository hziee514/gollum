package org.gollum.bank.eventhandler.logging;

import org.gollum.bank.Singleton;
import org.gollum.bank.domain.account.BankAccountCreated;
import org.gollum.core.eventing.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class BankAccountCreatedLoggingHandler implements EventHandler<BankAccountCreated> {
    @Override
    public void handle(BankAccountCreated event) {
        System.out.println(String.format("BankAccountCreated: id=%s, version=%d, owner=%s",
                event.getAggregateRootId(), event.getVersion(), event.getOwner()));
    }
}
