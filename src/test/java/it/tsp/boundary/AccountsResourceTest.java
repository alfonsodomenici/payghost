package it.tsp.boundary;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.hilling.junit.cdi.CdiTestJunitExtension;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import it.tsp.entity.Account;


public class AccountsResourceTest {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://127.0.0.1:8080/payghost/api/accounts/";
    }

    @Test
    public void testRegistration() throws JsonProcessingException{

        Account account = new Account("test", "user", "test.user@gmail.com", "1234");
        account.setConfirmPwd("1234");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(account);
        Response resp = RestAssured.given()
            .contentType("application/json")
            .body(json)
            .post();

        resp.then().assertThat().statusCode(equalTo(201));
        
    }
}
