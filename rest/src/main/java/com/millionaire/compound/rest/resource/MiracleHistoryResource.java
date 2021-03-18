package com.millionaire.compound.rest.resource;

import com.millionaire.compound.common.models.MiracleHistoryModel;
import com.millionaire.compound.common.models.StockModel;
import com.millionaire.compound.hibernate.entity.basic.MiracleHistory;
import com.millionaire.compound.stock.service.IMiracleHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("/history")
public class MiracleHistoryResource {

    @Autowired
    IMiracleHistoryService miracleHistoryService;

    @POST
    @Path("/open")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response openHistory(MiracleHistoryModel miracleHistoryModel) {

        miracleHistoryService.openHistoryRecord(miracleHistoryModel);

        return Response.ok().build();
    }

    @POST
    @Path("/close")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response closeHistory(MiracleHistoryModel miracleHistoryModel) {

        miracleHistoryService.closeHistoryRecord(miracleHistoryModel);

        return Response.ok().build();
    }



}
