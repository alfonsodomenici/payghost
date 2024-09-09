package it.tsp;

import org.eclipse.microprofile.auth.LoginConfig;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@DataSourceDefinition(
        name = "java:global/jdbc/payghost",
        className = "com.mysql.cj.jdbc.MysqlConnectionPoolDataSource",
        user = "${ENV=DB_USER}",        
        password = "${ENV=DB_PASSWORD}",
        url = "${ENV=DB_JDBC_URL}",
        properties = {
            "allowPublicKeyRetrieval=true",
            "useSSL=false",
            "requireSSL=false"
        }
)
@LoginConfig(authMethod = "MP-JWT", realmName = "MP-JWT")
@DeclareRoles({"USERS"})
@ApplicationPath("/api")
public class RestConfig extends Application {

}
