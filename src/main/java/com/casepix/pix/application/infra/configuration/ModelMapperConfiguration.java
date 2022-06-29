package com.casepix.pix.application.infra.configuration;

import com.casepix.pix.application.infra.converter.LocalDateConverter;
import com.casepix.pix.application.mapper.KeyDomainMapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapperBean() {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addConverter(new LocalDateConverter());
        return modelMapper;
    }

    @Bean
    public KeyDomainMapper keyDomainMapper(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return new KeyDomainMapper(modelMapper);
    }
}
