package com.geekseat.test;

import com.geekseat.test.services.TheWitchStandService;
import com.geekseat.test.services.impl.TheWitchStandServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ServiceConfiguration {
    @Bean
    public TheWitchStandService theWitchStandService() {
        return new TheWitchStandServiceImpl();
    }
}
