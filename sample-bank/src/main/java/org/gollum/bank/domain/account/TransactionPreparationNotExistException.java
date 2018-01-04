package org.gollum.bank.domain.account;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class TransactionPreparationNotExistException extends RuntimeException {

    public TransactionPreparationNotExistException(String accountId, String transactionId) {
        super(String.format("TransactionPreparation[transactionId=%s] not exist in account[id=%s]", accountId, transactionId));
    }

}
