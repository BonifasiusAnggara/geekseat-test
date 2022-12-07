package com.geekseat.test.util.mapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Component
public class DefaultModelMapper {

    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        modelMapper.addConverter(trimData());

        return modelMapper;
    }

    private Converter<String, String> trimData() {
        return new AbstractConverter<String, String>() {
            protected String convert(String source) {
                return source == null ? null : source.trim();
            }
        };
    }
}
