package com.millionaire.compound.livermore.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.SequenceGenerator;
import java.util.List;


public class LivermoreResponse {


    List<LivermoreModel> livermoreModelList;

    public void setLivermoreModelList(List<LivermoreModel> livermoreModelList) {
        this.livermoreModelList = livermoreModelList;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
             json = mapper.writeValueAsString( livermoreModelList );

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


}
