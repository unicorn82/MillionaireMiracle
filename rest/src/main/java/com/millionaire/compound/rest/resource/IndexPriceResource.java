package com.millionaire.compound.rest.resource;

import com.millionaire.compound.common.models.IndexPriceModel;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.stock.service.IIndexPriceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/index/price/")
public class IndexPriceResource {

    @Autowired
    IIndexPriceService indexPriceService;



    @POST
    @Path("/{ticker}/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveIndexPrice(@PathParam("ticker") String ticker, List<StockPriceModel> indexPriceModels) {
        indexPriceService.saveIndexDailyPrice(ticker, indexPriceModels);

        return Response.ok().build();
    }

    @POST
    @Path("/{ticker}/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateIndexPrice(@PathParam("ticker") String ticker) {
        indexPriceService.updateIndexDailyPrice(ticker);

        return Response.ok().build();
    }
}
