package it.tsp.control;

import java.math.BigDecimal;

import it.tsp.boundary.PayghostException;
import it.tsp.entity.Account;
import it.tsp.entity.Transaction;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class PayghostManager {

    @Inject
    AccountStore accountStore;

    @Inject
    TransactionStore transactionStore;

    public Transaction doTransaction(Account sender, Account receiver, BigDecimal amount) {
        if (!sender.hasSufficientCredit(amount)) {
            throw new PayghostException("insufficient credit");
        }
        Transaction tx = new Transaction(sender, receiver, amount);
        Transaction savedTx = transactionStore.saveTransaction(tx);
        sender.decreaseCredit(amount);
        receiver.increaseCredit(amount);
        accountStore.saveAccount(sender);
        accountStore.saveAccount(receiver);
        return savedTx;
    }
}
