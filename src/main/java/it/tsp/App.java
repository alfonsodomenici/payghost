package it.tsp;

import java.math.BigDecimal;
import java.util.Optional;
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

                /*
                 * Account alfonso = PayGhost.registration(
                 * "alfonso",
                 * "domenici",
                 * "alfonso.domenici@gmail.com",
                 * "1234",
                 * "1234", BigDecimal.valueOf(100));
                 * 
                 * System.out.println(alfonso);
                 * PayGhost.recharge(alfonso.getId(), BigDecimal.valueOf(50));
                 * 
                 * Account mario = PayGhost.registration(
                 * "mario",
                 * "rossi",
                 * "mario.rossi@gmail.com",
                 * "1234",
                 * "1234", BigDecimal.valueOf(100));
                 * 
                 * System.out.println(mario);
                 * PayGhost.recharge(mario.getId(), BigDecimal.valueOf(50));
                 * 
                 * PayGhost.sendMoney(alfonso.getId(), mario.getId(), BigDecimal.valueOf(70));
                 * PayGhost.sendMoney(alfonso.getId(), mario.getId(), BigDecimal.valueOf(20));
                 * PayGhost.sendMoney(mario.getId(), alfonso.getId(), BigDecimal.valueOf(40));
                 * System.out.println(alfonso);
                 * System.out.println(mario);
                 * 
                 * System.out.println("---------------  alfonso transaction ");
                 * PayGhost.transactionByUser(alfonso.getId())
                 * .forEach(v ->
                 * System.out.println(String.format("sender: %s, receiver: %s, amount: %s",
                 * v.getSender().getFullname(), v.getReceiver().getFullname(),
                 * v.viewAmount(alfonso.getId()))));
                 * 
                 * System.out.println("---------------  mario transaction ");
                 * PayGhost.transactionByUser(mario.getId())
                 * .forEach(v ->
                 * System.out.println(String.format("sender: %s, receiver: %s, amount: %s",
                 * v.getSender().getFullname(), v.getReceiver().getFullname(),
                 * v.viewAmount(mario.getId()))));
                 * 
                 * PayGhost.rechargeByUser(alfonso.getId()).forEach(System.out::println);
                 * 
                 * Optional<Account> jdbcAccount = Store.jdbcFindAccountById(1);
                 * System.out.println(jdbcAccount);
                 * 
                 * Store.jdbcFindAllAccount()
                 * .forEach(System.out::println);
                 * 
                 */

                System.out.println("--------------- alfonso transactions ------------ ");
                Store.jpaFindAllTransaction()
                                .forEach(v -> System.out.println(v));

        }
}
