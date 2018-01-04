package org.gollum.bank.domain.deposit;

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
public class DepositTransactionRepository extends BaseRepository<DepositTransaction> {

    @Autowired
    public DepositTransactionRepository(EventStorage storage, EventBus publisher) {
        super(storage, publisher);
    }

}
