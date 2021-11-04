package pt.ipleiria.estg.dei.ei.dae.academics.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.logging.Logger;

public class CatchAllExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger logger = Logger.getLogger("exceptions.Exception");

    @Override
    public Response toResponse(Exception e) {
        String errorMsg = e.getMessage();
        logger.warning("ERROR: " + errorMsg);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMsg).build();
    }
}
