package pt.ipleiria.estg.dei.ei.dae.academics.exceptions;

import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyEntityExistsExceptionMapper implements ExceptionMapper<MyEntityExistsException> {
    private static final Logger logger = Logger.getLogger("exceptions.MyEntityExistsExceptionMapper");

    @Override
    public Response toResponse(MyEntityExistsException e) {
        String errorMsg = e.getMessage();
        logger.warning("ERROR: " + errorMsg);

        return Response.status(Response.Status.CONFLICT).entity(errorMsg).build();
    }
}
