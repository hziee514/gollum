package org.gollum.bank.domain.transfer;

import org.gollum.bank.Singleton;
import org.gollum.core.domain.BaseRepository;
import org.gollum.core.eventing.EventBus;
import org.gollum.core.eventing.EventStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/15
 */
@Component
@Singleton
public class TransferTransactionRepository extends BaseRepository<TransferTransaction> {

    @Autowired
    public TransferTransactionRepository(EventStorage storage, EventBus publisher) {
        super(storage, publisher);
    }

}
