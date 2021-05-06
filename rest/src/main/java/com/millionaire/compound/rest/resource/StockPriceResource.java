package com.millionaire.compound.rest.resource;

import com.millionaire.compound.common.models.StockModel;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.stock.service.IStockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@Path("/stock/price/")
public class StockPriceResource {

    @Autowired
    IStockPriceService stockPriceService;

    @POST
    @Path("/{ticker}/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveStockPrice(@PathParam("ticker") String ticker, List<StockPriceModel> stockPriceModels) {
        stockPriceService.saveStockDailyPrice(ticker, stockPriceModels);
        return Response.ok().build();
    }

    @POST
    @Path("/{ticker}/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStockPrice(@PathParam("ticker") String ticker) {
        stockPriceService.updateStockDailyPrice(ticker);
        return Response.ok().build();
    }

    @GET
    @Path("/candidates")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStockDailyCandidate() {
        return Response.ok(stockPriceService.getPotentialStocks()).build();

    }

    @GET
    @Path("/{date}/candidates")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStockDailyCandidate(@PathParam("date") String date) {
        return Response.ok(stockPriceService.getPotentialStocks(date)).build();

    }


}
