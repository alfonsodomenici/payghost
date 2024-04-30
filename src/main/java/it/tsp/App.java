package it.tsp;

import it.tsp.control.Store;
import it.tsp.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Account a = new Account("alfonso.domenici@gmail.com", "1234");

        Account saved = Store.saveAccount(a);

        System.out.println(saved);
    }
}
