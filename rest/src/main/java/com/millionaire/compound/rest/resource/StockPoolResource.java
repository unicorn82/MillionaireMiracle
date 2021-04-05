package com.millionaire.compound.rest.resource;


import com.millionaire.compound.common.models.StockModel;
import com.millionaire.compound.stock.service.IStockPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("/stock")
public class StockPoolResource {

    @Autowired
    IStockPoolService stockPoolService;


    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveStockDish(StockModel stockModel) {

        stockPoolService.saveStockItem(stockModel);

        return Response.ok().build();
    }


    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStocks() {
        return Response.ok(stockPoolService.listAllStocks()).build();
    }
}
