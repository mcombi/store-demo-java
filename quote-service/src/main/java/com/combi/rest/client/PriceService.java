package com.combi.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.Set;

@Path("/prices")
@RegisterRestClient
public interface PriceService {

    @GET
    Price getRandom();

    @GET
    @Path("prices/{id}")
    Set<Price> getById(@PathParam("id") String id);
}
