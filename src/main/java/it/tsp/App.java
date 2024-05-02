package it.tsp;

import java.math.BigDecimal;
import java.util.Set;

import it.tsp.boundary.PayGhost;
import it.tsp.control.Store;
import it.tsp.entity.Account;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        /*
         * ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
         * Validator validator = vf.getValidator();
         * Set<ConstraintViolation<Account>> result = validator.validate(account);
         * result.forEach(System.out::println);
         */

        Account saved = PayGhost.registration(
                "alfonso",
                "domenici",
                "alfonso.domenici@gmail.com",
                "1234",
                "1234", BigDecimal.valueOf(100));

        System.out.println(saved);
    }
}
