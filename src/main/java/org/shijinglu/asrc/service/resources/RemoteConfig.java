package org.shijinglu.asrc.service.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.shijinglu.asrc.core.Service;
import org.shijinglu.asrc.service.api.GetConfigRequest;
import org.shijinglu.asrc.service.utils.Mapper;
import org.shijinglu.lure.extensions.IData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class RemoteConfig {
    private final Logger logger = LoggerFactory.getLogger(RemoteConfig.class);

    private final Service service;

    public RemoteConfig(Service service) {
        this.service = service;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/sendUser")
    public Response sendUser(MultivaluedMap<String, String> usersMap) {
        logger.info("userMap = {}", usersMap);
        return Response.ok("Get user attributes successfully !").build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/getConfig")
    public Response getConfig(GetConfigRequest request) {
        Map<String, IData> res = service.getConfigs(request.getNamespace(), Mapper.mapRequestConfig(request.getContext()));
        logger.info("Response: {}", res);
        return Response.ok(Mapper.mapConfigToResponse(res)).build();

    }
}
