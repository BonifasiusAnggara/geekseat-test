package com.geekseat.test.ui.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonsDto {
    @NotNull(message = "Person 1 is Required")
    PersonDto person1;
    @NotNull(message = "Person 2 is Required")
    PersonDto person2;

    @Override
    public String toString() {
        return "PersonsDto{" +
                "person1=" + person1 +
                ", person2=" + person2 +
                '}';
    }
}
