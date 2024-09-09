package it.tsp.boundary;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.WaitStrategy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.model.WaitResponse;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import it.tsp.dto.CredentialDTO;
import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Paths;

@Testcontainers
public class AuthsResourceTest {

    /* 
    MountableFile warFile = MountableFile.forHostPath(Paths.get("target/payghost.war").toAbsolutePath(), 0777);

    @Container
    GenericContainer gc = new GenericContainer<>(DockerImageName.parse("mysql:8.0"));

    @Container
    GenericContainer wf = new GenericContainer<>(DockerImageName.parse("quay.io/wildfly/wildfly:30.0.1.Final-jdk17"))
            .withExposedPorts(8080)
            .withCopyFileToContainer(warFile, "/opt/jboss/wildfly/standalone/deployments/");
           
    @BeforeAll
    public  void init() {
        wf.start();
        RestAssured.baseURI = "http://127.0.0.1:8080/payghost/api/auths/";
    }

    public void testRegistration() {

    }

    @Test
    public void testLoginFailed() throws JsonProcessingException {

        CredentialDTO credential = new CredentialDTO("xx@gmail.com", "xx");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(credential);

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(json)
                .post();

        resp.then().assertThat().statusCode(equalTo(401));

    }

    */
}
