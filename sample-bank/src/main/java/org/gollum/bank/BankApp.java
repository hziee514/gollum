package org.gollum.bank;

import org.gollum.bank.command.CreateAccountCommand;
import org.gollum.bank.command.StartDepositTransactionCommand;
import org.gollum.core.commanding.CommandBus;
import org.gollum.core.common.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class BankApp {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BankAppConfig.class);
        CommandBus bus = ctx.getBean(CommandBus.class);

        ObjectId idGen = new ObjectId();

        String account1 = "1111";
        String account2 = "2222";
        String account3 = "3333";

        System.out.println("account1=" + account1);
        System.out.println("account2=" + account2);

        bus.sendSync(new CreateAccountCommand(account1, "冬去"));
        bus.sendSync(new CreateAccountCommand(account2, "春来"));

        Thread.sleep(100);
        bus.send(new StartDepositTransactionCommand(idGen.newStringId("DT"), account1, 1000));
        Thread.sleep(100);
        bus.send(new StartDepositTransactionCommand(idGen.newStringId("DT"), account2, 1000));
        Thread.sleep(100);
        bus.send(new StartDepositTransactionCommand(idGen.newStringId("DT"), account1, 2000));

        Thread.sleep(100);
        bus.send(new StartDepositTransactionCommand(idGen.newStringId("DT"), account3, 1000));

        System.out.println();
    }

}
