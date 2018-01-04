package org.gollum.bank.domain;

/**
 * 交易状态
 *
 * @author wurenhai
 * @date 2018/1/4
 */
public enum TransactionStatus {

    /**
     * 已开始
     */
    Started,

    /**
     * 验证帐号
     */
    AccountValidateCompleted,

    /**
     * 交易准备
     */
    PreparationCompleted,

    /**
     * 交易已完成
     */
    Completed,

    /**
     * 交易已取消
     */
    Cancelled,

}
