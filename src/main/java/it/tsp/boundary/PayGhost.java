package it.tsp.boundary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import it.tsp.control.EncodeUtils;
import it.tsp.control.AccountStore;
import it.tsp.entity.Account;
import it.tsp.entity.Recharge;
import it.tsp.entity.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PayGhost {

    @Inject
    AccountStore accountStore;

    public  Account login(String email, String pwd){
        Account account = accountStore.findAccountByUsr(email).orElseThrow(() -> new LoginFailedException("usr non trovato"));
        
        if(EncodeUtils.verify(pwd,account.getPwd())){
            return account;
        }

        throw new LoginFailedException("password non corretta");
    } 

    public  Account registration(String fname, String lname, String email,
            String pwd, String confirmPwd, BigDecimal credit) {

                throw new UnsupportedOperationException();

    }

    public  void recharge(long accountId, BigDecimal amount) {
        /* 
        try {
            // nuovo oggetto Recharge
            Account account = accountStore.findAccountById(accountId)
                    .orElseThrow(() -> new RechargeException("account non trovato: " + accountId));
                    new Recharge(account, amount).saveRecharge(accountStore);
            account.increaseCredit(amount);
            accountStore.saveAccount(account);
        } catch (Exception ex) {
            throw new RechargeException(ex.getMessage());
        }
        */
    }

    public  void sendMoney(long senderId, long receiverId, BigDecimal amount) {
        /*
        try {
            Account sender = accountStore.findAccountById(senderId)
                    .orElseThrow(() -> new TransactionException("account non trovato: " + senderId));
            Account receiver = accountStore.findAccountById(receiverId)
                    .orElseThrow(() -> new TransactionException("account non trovato: " + receiverId));
            if (!sender.hasSufficientCredit(amount)) {
                throw new TransactionException("Credito insufficiente per: " + sender);
            }
            accountStore.saveTransaction(new Transaction(sender, receiver, amount));
            receiver.increaseCredit(amount);
            sender.decreaseCredit(amount);
            accountStore.saveAccount(receiver);
            accountStore.saveAccount(sender);
        } catch (Exception ex) {
            throw new TransactionException(ex.getMessage());
        }
         */
    }

    public  List<Transaction> transactionByUser(long accountId) {
        throw new UnsupportedOperationException();
        /*
        return accountStore.findTransactionsByAccountId(accountId);
         */
    }

    public  List<Recharge> rechargeByUser(long accountId) {
        throw new UnsupportedOperationException();
        /*
        return accountStore.findRechargesByAccountId(accountId);
         */
    }
}
