package com.back.carfind;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Convierte entidades en DTOs y DTOs en entidades (lo usan los controllers de com.back.carfind.controller).
 * STRICT: solo copia campos con exactamente el mismo nombre, para no mezclar, por ejemplo,
 * usuario1Id con usuario2Id.
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }
}
