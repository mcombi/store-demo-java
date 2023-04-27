package com.combi;

import com.combi.model.PriceQuota;
import io.quarkus.logging.Log;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Path("/price")
public class ProductPriceResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PriceQuota price() {
        Log.infof("Received price request");
        int min=0;
        int max=199;

        PriceQuota p=new PriceQuota();
        p.setDescription("Price description from: " + getAddress());
        p.setItemId(0L);
        p.setItemName("Test item");
        p.setPrice( Math.floor(Math.random() *(max - min + 1) + min));
        Log.infof("price request for "+p.getDescription());
        return p;
    }

    private String getAddress(){
        InetAddress addr = null;
        try {
             addr = java.net.InetAddress.getLocalHost();



        } catch (UnknownHostException e) {
            Log.error(e.getMessage());
        }
        return addr.getHostName();
    }
}