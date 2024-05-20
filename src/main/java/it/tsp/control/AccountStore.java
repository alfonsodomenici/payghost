package it.tsp.control;

import java.util.Optional;
import java.util.List;

import it.tsp.dto.TxEssential;
import it.tsp.entity.Account;
import it.tsp.entity.Recharge;
import it.tsp.entity.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AccountStore {

    @PersistenceContext(unitName = "payghost")
    private  EntityManager em = null;


    public  Account saveAccount(Account e) {
        Account saved = em.merge(e);
        return saved;
    }

    public  Recharge saveRecharge(Recharge e) {
        Recharge saved = em.merge(e);
        return saved;
    }

    public  Transaction saveTransaction(Transaction e) {
        Transaction saved = em.merge(e);
        return saved;
    }

    public  Optional<Account> findAccountById(long accountId) {
        Account account = em.find(Account.class, accountId);
        return account == null ? Optional.empty() : Optional.of(account);
    }

    public  List<Recharge> findRechargesByAccountId(long accountId) {
        return em.createNamedQuery(Recharge.FIND_BY_ACCOUNT_ID, Recharge.class)
                .setParameter("id", accountId)
                .getResultList();
    }

    public  List<Transaction> findTransactionsByAccountId(long accountId) {
        List<Transaction> resultList = em.createNamedQuery(Transaction.FIND_BY_ACCOUNT_ID, Transaction.class)
                .setParameter("id", accountId)
                .getResultList();
        return resultList;
    }

    public  List<Transaction> findTransactionsByAccountIdFetchAll(long accountId) {
        List<Transaction> resultList = em
                .createQuery(
                        "select e from Transaction e join fetch e.sender join fetch e.receiver where e.sender.id= :id",
                        Transaction.class)
                .setParameter("id", accountId)
                .getResultList();
        return resultList;
    }

    public  List<TxEssential> findAllTransaction() {
        return em.createQuery(
                " select new it.tsp.dto.TxEssential( e.sender.fname,e.sender.lname, e.receiver.fname,e.receiver.lname, e.amount,e.performedOn) from Transaction e ",
                TxEssential.class)
                .getResultList();
    }

    public  Optional<Account> findAccountByUsr(String email) {
        List<Account> result = em.createNamedQuery(Account.FIND_BY_USR, Account.class)
            .setParameter("email", email)
            .getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

}
