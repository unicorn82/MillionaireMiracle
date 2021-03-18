package com.millionaire.compound.rest.resource;

import com.millionaire.compound.common.models.IndexPriceModel;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.stock.service.IIndexPriceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/index/price/")
public class IndexPriceResource {

    @Autowired
    IIndexPriceService indexPriceService;

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveIndexPrice(List<StockPriceModel> indexPriceModels) {
        indexPriceService.saveIndexDailyPrice(indexPriceModels);

        return Response.ok().build();
    }
}
