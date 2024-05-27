package it.tsp.control;

import java.io.Serializable;
import java.util.List;

import it.tsp.entity.Recharge;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class RechargeStore implements Serializable {

    @PersistenceContext(unitName = "payghost")
    private EntityManager em;

    public Recharge saveRecharge(Recharge e) {
        Recharge saved = this.em.merge(e);
        return saved;
    }

    public List<Recharge> findRechargesByAccountId(long accountId) {
        return em.createNamedQuery(Recharge.FIND_BY_ACCOUNT_ID, Recharge.class)
                .setParameter("id", accountId)
                .getResultList();
    }
}
