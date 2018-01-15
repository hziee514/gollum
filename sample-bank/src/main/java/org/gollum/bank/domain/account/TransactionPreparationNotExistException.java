package org.gollum.bank.domain.account;

import org.gollum.common.exception.GollumException;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class TransactionPreparationNotExistException extends GollumException {

    public TransactionPreparationNotExistException(String accountId, String transactionId) {
        super(String.format("TransactionPreparation[transactionId=%s] not exist in account[id=%s]", accountId, transactionId));
    }

}
