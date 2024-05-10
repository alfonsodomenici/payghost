package it.tsp.control;

import java.util.Optional;
import java.util.List;

import it.tsp.dto.TxEssential;
import it.tsp.entity.Account;
import it.tsp.entity.Recharge;
import it.tsp.entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Store {

    // private static EntityManagerFactory emf =
    // Persistence.createEntityManagerFactory(Store.JPA_PU);
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(Store.JPA_PU);
    private static EntityManager em = null;

    private static final String JPA_PU = "payghost";

    // costruttore static
    static {
        System.out.println("create entity manager");
        em = emf.createEntityManager();
    }

    public static void beginTran() {
        if (em.getTransaction().isActive()) {
            throw new StoreException("Esiste già una transazione attiva");
        }
        em.getTransaction().begin();
        ;
    }

    public static void commitTran() {
        if (!em.getTransaction().isActive()) {
            throw new StoreException("Nessuna transazione attiva");
        }
        em.getTransaction().commit();
    }

    public static void rollTran() {
        if (!em.getTransaction().isActive()) {
            return;
        }
        em.getTransaction().rollback();
    }

    public static Account saveAccount(Account e) {
        if (em.getTransaction().isActive()) {
            return em.merge(e);
        }
        em.getTransaction().begin();
        Account saved = em.merge(e);
        em.getTransaction().commit();
        return saved;
    }

    public static Recharge saveRecharge(Recharge e) {
        if (em.getTransaction().isActive()) {
            return em.merge(e);
        }
        em.getTransaction().begin();
        Recharge saved = em.merge(e);
        em.getTransaction().commit();
        return saved;
    }

    public static Transaction saveTransaction(Transaction e) {
        if (em.getTransaction().isActive()) {
            return em.merge(e);
        }
        em.getTransaction().begin();
        Transaction saved = em.merge(e);
        em.getTransaction().commit();
        return saved;
    }

    public static Optional<Account> findAccountById(long accountId) {
        Account account = em.find(Account.class, accountId);
        return account == null ? Optional.empty() : Optional.of(account);
    }

    public static List<Recharge> findRechargesByAccountId(long accountId) {
        return em.createNamedQuery(Recharge.FIND_BY_ACCOUNT_ID, Recharge.class)
                .setParameter("id", accountId)
                .getResultList();
    }

    public static List<Transaction> findTransactionsByAccountId(long accountId) {
        List<Transaction> resultList = em.createNamedQuery(Transaction.FIND_BY_ACCOUNT_ID, Transaction.class)
                .setParameter("id", accountId)
                .getResultList();
        return resultList;
    }

    public static List<Transaction> findTransactionsByAccountIdFetchAll(long accountId) {
        List<Transaction> resultList = em
                .createQuery(
                        "select e from Transaction e join fetch e.sender join fetch e.receiver where e.sender.id= :id",
                        Transaction.class)
                .setParameter("id", accountId)
                .getResultList();
        return resultList;
    }

    public static List<TxEssential> findAllTransaction() {
        return em.createQuery(
                " select new it.tsp.dto.TxEssential( e.sender.fname,e.sender.lname, e.receiver.fname,e.receiver.lname, e.amount,e.performedOn) from Transaction e ",
                TxEssential.class)
                .getResultList();
    }

    public static Optional<Account> findAccountByUsrAndPwd(String email, String pwd) {
        List<Account> result = em.createNamedQuery(Account.FIND_BY_USR_AND_PWD, Account.class)
            .setParameter("email", email)
            .setParameter("pwd", pwd)
            .getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

}
