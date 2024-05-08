package it.tsp.control;

import java.util.Optional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.tsp.dto.TxEssential;
import it.tsp.entity.Account;
import it.tsp.entity.Recharge;
import it.tsp.entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Store {

    // private static EntityManagerFactory emf =
    // Persistence.createEntityManagerFactory(Store.JPA_PU);
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(Store.JPA_PU);
    private static EntityManager em = null;

    private static final String JPA_PU = "payghost";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/payghost";
    private static final String JDBC_USR = "payghost";
    private static final String JDBC_PWD = "payghost";

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

    public static Optional<Account> jdbcFindAccountById(long accountId) {
        try (Connection cn = DriverManager.getConnection(JDBC_URL, JDBC_USR, JDBC_PWD);
                Statement stm = cn.createStatement();
                ResultSet rs = stm.executeQuery("select * from account where id=" + accountId);) {

            if (rs.next() == false) {
                return Optional.empty();
            }
            Account found = new Account(rs.getString("fname"), rs.getString("lname"), rs.getString("email"),
                    rs.getString("pwd"));
            found.setId(rs.getLong("id"));
            found.setCredit(rs.getBigDecimal("credit"));
            return Optional.of(found);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("errore nel metodo jdbc..");
        }

    }

    public static List<Account> jdbcFindAllAccount() {
        try (Connection cn = DriverManager.getConnection(JDBC_URL, JDBC_USR, JDBC_PWD);
                Statement stm = cn.createStatement();
                ResultSet rs = stm.executeQuery("select * from account");) {
            List<Account> result = new ArrayList<>();
            while (rs.next()) {
                Account a = new Account(rs.getString("fname"), rs.getString("lname"), rs.getString("email"),
                        rs.getString("pwd"));
                a.setId(rs.getLong("id"));
                a.setCredit(rs.getBigDecimal("credit"));
                result.add(a);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("errore nel metodo jdbc..");
        }

    }

    public static List<TxEssential> jpaFindAllTransaction() {
        return em.createQuery(
                " select new it.tsp.dto.TxEssential( e.sender.fname,e.sender.lname, e.receiver.fname,e.receiver.lname, e.amount,e.performedOn) from Transaction e ",
                TxEssential.class)
                .getResultList();
    }

}
