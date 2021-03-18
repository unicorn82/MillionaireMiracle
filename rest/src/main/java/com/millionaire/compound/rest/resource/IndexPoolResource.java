package com.millionaire.compound.rest.resource;


import com.millionaire.compound.common.models.IndexModel;
import com.millionaire.compound.common.models.StockModel;
import com.millionaire.compound.stock.service.IIndexPoolService;
import com.millionaire.compound.stock.service.impl.IndexPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("/index")
public class IndexPoolResource {

    @Autowired
    IIndexPoolService indexPoolService;

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveIndexDish(IndexModel indexModel) {

        System.out.println("saveIndexDish = " + indexModel);

        indexPoolService.saveIndexItem(indexModel);

        return Response.ok().build();
    }

    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIndex() {
        return Response.ok(indexPoolService.listAllIndexes()).build();
    }

}
