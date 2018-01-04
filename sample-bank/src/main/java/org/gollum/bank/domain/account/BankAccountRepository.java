package org.gollum.bank.domain.account;

import org.gollum.bank.Singleton;
import org.gollum.core.domain.BaseRepository;
import org.gollum.core.eventing.EventBus;
import org.gollum.core.eventing.EventStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class BankAccountRepository extends BaseRepository<BankAccount> {

    @Autowired
    public BankAccountRepository(EventStorage storage, EventBus publisher) {
        super(storage, publisher);
    }

}
