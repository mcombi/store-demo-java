package com.combi;

import com.combi.model.Quote;
import com.combi.rest.client.Price;
import com.combi.rest.client.PriceService;
import com.oracle.svm.core.annotate.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/quote")
public class QuoteResource {

    @Inject
    @RestClient
    PriceService pricesService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Quote getRandomQuote(){
        Quote q =new Quote();

        Price p = pricesService.getRandom();
        q.description=p.getDescription();
        q.name=p.getItemName();
        q.price=p.getPrice();


        return q;
    }

}