package com.millionaire.compound.rest.resource;

import com.millionaire.compound.common.models.StockModel;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.stock.service.IStockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@Path("/stock/price/")
public class StockPriceResource {

    @Autowired
    IStockPriceService stockPriceService;

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveStockPrice(List<StockPriceModel> stockPriceModels) {
        stockPriceService.saveStockDailyPrice(stockPriceModels);
        return Response.ok().build();
    }


}
