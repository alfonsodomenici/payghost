package it.tsp.boundary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import it.tsp.control.AccountStore;
import it.tsp.control.EncodeUtils;
import it.tsp.control.RechargeStore;
import it.tsp.dto.AccountSlice;
import it.tsp.entity.Account;
import it.tsp.entity.Recharge;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.json.stream.JsonCollectors;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@RequestScoped
@Path("/accounts")
public class AccountsResource {

    @Inject
    AccountStore accountStore;

    @Inject
    RechargeStore rechargeStore;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Response registration(@Valid Account account) {
        try {
            if (!Objects.equals(account.getPwd(), account.getConfirmPwd())) {
                throw new RegistrationException("le password non corrispondono");
            }

            // account.setPwd(EncodeUtils.encode(account.getPwd()));

            Account saved = accountStore.saveAccount(account);

            if (account.getCredit().compareTo(BigDecimal.ZERO) > 0) {
                Recharge recharge = new Recharge(saved, account.getCredit());
                rechargeStore.saveRecharge(recharge);
            }
            return Response
                    .status(Status.CREATED)
                    .entity(saved.getId())
                    .build();
        } catch (Exception ex) {
            throw new RegistrationException(ex.getMessage());
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Account> result = accountStore.findAll();
        List<AccountSlice> convertedResult = result.stream()
                .map(v -> new AccountSlice(v.getId(), v.getFname(), v.getLname()))
                .collect(Collectors.toList());
        return Response.ok(convertedResult).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        Optional<Account> result = accountStore.findAccountById(id);
        return result.isPresent() ? Response.ok(result.get()).build()
                : Response.status(Status.NOT_FOUND).build();
    }
}
