package com.geekseat.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekseat.test.ui.controller.TheWitchCraft;
import com.geekseat.test.ui.model.PersonDto;
import com.geekseat.test.ui.model.PersonsDto;
import com.geekseat.test.util.http.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TheWitchCraft.class)
@Import(ServiceConfiguration.class)
public class RestApiTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getCorrectResult() throws Exception {
        PersonsDto personsDto = new PersonsDto(List.of(
                PersonDto.builder()
                        .ageOfDeath(10)
                        .yearOfDeath(12)
                        .build(),
                PersonDto.builder()
                        .ageOfDeath(13)
                        .yearOfDeath(17)
                        .build()
        ));
        mvc.perform( MockMvcRequestBuilders
                        .post("/witch/death-average")
                        .content(asJsonString(personsDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("4.5"));
    }

    @Test
    public void yearOfDeathLessThanZero() throws Exception {
        // On year of (witch) death less than zero, we would expect an exception
        PersonsDto personsDto = new PersonsDto(List.of(
                PersonDto.builder()
                        .ageOfDeath(10)
                        .yearOfDeath(12)
                        .build(),
                PersonDto.builder()
                        .ageOfDeath(13)
                        .yearOfDeath(11) // 11 -13 is less than zero
                        .build()
        ));
        mvc.perform( MockMvcRequestBuilders
                        .post("/witch/death-average")
                        .content(asJsonString(personsDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException))
                .andExpect(result -> assertEquals("The witch is not take control yet", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
