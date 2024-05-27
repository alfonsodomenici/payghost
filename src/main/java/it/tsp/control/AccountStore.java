package it.tsp.control;

import java.util.Optional;
import java.io.Serializable;
import java.util.List;

import it.tsp.dto.TxEssential;
import it.tsp.entity.Account;
import it.tsp.entity.Recharge;
import it.tsp.entity.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AccountStore implements Serializable {

    @PersistenceContext(unitName = "payghost")
    private EntityManager em;

    public Account saveAccount(Account e) {
        Account saved = em.merge(e);
        return saved;
    }

    public List<Account> findAll() {
        return em.createQuery("select e from Account e", Account.class)
                .getResultList();
    }

    public Optional<Account> findAccountById(long accountId) {
        Account account = em.find(Account.class, accountId);
        return account == null ? Optional.empty() : Optional.of(account);
    }

    public Optional<Account> findAccountByUsr(String email) {
        List<Account> result = em.createNamedQuery(Account.FIND_BY_USR, Account.class)
                .setParameter("email", email)
                .getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

}
