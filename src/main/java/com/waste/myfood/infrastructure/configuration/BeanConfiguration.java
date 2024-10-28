package com.waste.myfood.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.waste.myfood.application.output.ExceptionFormatterIntPort;
import com.waste.myfood.application.output.ManageWasteGatewayIntPort;
import com.waste.myfood.domain.use_cases.ManageWasteCUImplAdapter;
import com.waste.myfood.infrastructure.input.mappers.MapperWasteInfraestructureDomain;
import com.waste.myfood.infrastructure.output.persistence.mapper.MapperWastePersistenceDomain;

@Configuration
public class BeanConfiguration {
    @Bean
    public ManageWasteCUImplAdapter createWasteCUImplAdapter(ManageWasteGatewayIntPort gateway,
            ExceptionFormatterIntPort exceptionFormatter){
        return new ManageWasteCUImplAdapter(gateway, exceptionFormatter);
    }

    @Bean
    public MapperWasteInfraestructureDomain createMapperWasteInfrastructureDomain(
            ExceptionFormatterIntPort exceptionFormatter){
        return new MapperWasteInfraestructureDomain(exceptionFormatter);
    }

    @Bean
    public MapperWastePersistenceDomain createMapperProductPersistenceDomain(
            ExceptionFormatterIntPort exceptionFormatter){
        return new MapperWastePersistenceDomain(exceptionFormatter);
    }
}
