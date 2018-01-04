package org.gollum.bank.domain;

/**
 * 交易类型
 *
 * @author wurenhai
 * @date 2018/1/4
 */
public enum TransactionType {

    /**
     * 存款
     */
    DepositTransaction,

    /**
     * 取款
     */
    WithdrawTransaction,

    /**
     * 转账
     */
    TransferTransaction

}
