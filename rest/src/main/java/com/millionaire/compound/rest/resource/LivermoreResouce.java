package com.millionaire.compound.rest.resource;

import com.millionaire.compound.livermore.service.IlivermoreService;
import com.millionaire.compound.stock.service.IIndexPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/livermore")
public class LivermoreResouce {


    @Autowired
    IlivermoreService indexPriceService;

    @GET
    @Path("/ticker/{ticker}/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickerLivermore(@PathParam("ticker") String ticker) {
        return Response.ok(indexPriceService.processLivermoreForTicker(ticker)).build();
    }


}
