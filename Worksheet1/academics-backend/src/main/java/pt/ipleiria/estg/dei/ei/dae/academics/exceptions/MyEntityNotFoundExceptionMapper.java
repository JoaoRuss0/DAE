package pt.ipleiria.estg.dei.ei.dae.academics.exceptions;

import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyEntityNotFoundExceptionMapper implements ExceptionMapper<MyEntityNotFoundException> {
    private static final Logger logger = Logger.getLogger("exceptions.MyEntityNotFoundExceptionMapper");

    @Override
    public Response toResponse(MyEntityNotFoundException e) {
        String errorMsg = e.getMessage();
        logger.warning("ERROR: " + errorMsg);

        return Response.status(Response.Status.NOT_FOUND).entity(errorMsg).build();
    }
}
