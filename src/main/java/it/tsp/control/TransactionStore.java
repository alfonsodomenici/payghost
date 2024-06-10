package it.tsp.control;

import java.io.Serializable;
import java.util.List;

import it.tsp.dto.TxEssential;
import it.tsp.entity.Transaction;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class TransactionStore implements Serializable {

    @PersistenceContext(unitName = "payghost")
    private EntityManager em;

    public Transaction saveTransaction(Transaction e) {
        Transaction saved = em.merge(e);
        return saved;
    }

    public List<Transaction> findTransactionsByAccountId(long accountId) {
        List<Transaction> resultList = em.createNamedQuery(Transaction.FIND_BY_ACCOUNT_ID, Transaction.class)
                .setParameter("id", accountId)
                .getResultList();
        return resultList;
    }

    public List<Transaction> findTransactionsByAccountIdFetchAll(long accountId) {
        List<Transaction> resultList = em
                .createQuery(
                        "select e from Transaction e join fetch e.sender join fetch e.receiver where e.sender.id= :id",
                        Transaction.class)
                .setParameter("id", accountId)
                .getResultList();
        return resultList;
    }

    public List<TxEssential> findAllTransaction() {
        return em.createQuery(
                " select new it.tsp.dto.TxEssential( e.sender.fname,e.sender.lname, e.receiver.fname,e.receiver.lname, e.amount,e.performedOn) from Transaction e ",
                TxEssential.class)
                .getResultList();
    }
}
