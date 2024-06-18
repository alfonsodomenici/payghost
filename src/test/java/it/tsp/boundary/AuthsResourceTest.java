package it.tsp.boundary;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import it.tsp.dto.CredentialDTO;
import jakarta.ws.rs.core.Response.Status;
import static org.hamcrest.Matchers.equalTo;

public class AuthsResourceTest {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://127.0.0.1:8080/payghost/api/auths/";
    }

    public void testRegistration(){
        
    }

    @Test
    public void testLoginFailed() throws JsonProcessingException{

        CredentialDTO credential = new CredentialDTO("xx@gmail.com", "xx");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(credential);


        Response resp = RestAssured.given()
            .contentType("application/json")
            .body(json)
            .post();

        resp.then().assertThat().statusCode(equalTo(401));
    }
}
