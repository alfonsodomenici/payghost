package it.tsp.control;

import it.tsp.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Store {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("payghost");
    private static EntityManager em = emf.createEntityManager();

    //costruttore static 
    {
       
    }

    public static Account saveAccount(Account e){
        em.getTransaction().begin();
        Account saved = em.merge(e);
        em.getTransaction().commit();
        return saved;
    }
}
