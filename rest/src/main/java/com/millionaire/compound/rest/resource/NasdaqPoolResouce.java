package com.millionaire.compound.rest.resource;


import com.millionaire.compound.common.models.NasdaqStockModel;
import com.millionaire.compound.nasdaq.service.impl.NasdaqService;
import org.glassfish.hk2.classmodel.reflect.AnnotationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.millionaire.compound.nasdaq.service.INasdaqService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("/nasdaq")
public class NasdaqPoolResouce {

    @Autowired
    private INasdaqService nasdaqService;

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveNasdaq(NasdaqStockModel nasdaqStockModel) {
        System.out.println(nasdaqStockModel.toString());
        nasdaqService.saveNasdaqItem(nasdaqStockModel);

        return Response.ok().build();
    }


    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNasdaq() {

        nasdaqService.listNasdaqItems();
        return Response.ok(nasdaqService.listNasdaqItems()).build();
    }
}
