package it.tsp;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PayghostSecurityExceptionMapper implements ExceptionMapper<PayghostSecurityException> {

    @Override
    public Response toResponse(PayghostSecurityException ex) {
        return Response.status(Status.UNAUTHORIZED)
            .header("caused-by",ex.getMessage() )
            .build();
    }

}
