package com.millionaire.compound.rest.config;

import com.millionaire.compound.rest.resource.NasdaqPoolResouce;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath(RestCommon.APPLICATION_PATH_NAME)
public class RestConfig extends ResourceConfig {

    public RestConfig() {
        System.out.println("rest config init");
        super.register(NasdaqPoolResouce.class);

    }

}
