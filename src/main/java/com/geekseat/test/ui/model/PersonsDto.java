package com.geekseat.test.ui.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonsDto {
    @NotNull(message = "Persons is Required")
    @NotEmpty(message = "Person list cannot be empty")
    @Valid
    private List<PersonDto> persons;
}
