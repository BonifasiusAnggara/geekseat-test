package com.geekseat.test.ui.model;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    @NotNull(message = "Age of Death is Required")
    private Integer ageOfDeath;

    @NotNull(message = "Year of Death is Required")
    private Integer yearOfDeath;

    @Override
    public String toString() {
        return "PersonDto{" +
                "ageOfDeath=" + ageOfDeath +
                ", yearOfDeath=" + yearOfDeath +
                '}';
    }

    public int getWitchYear() {
        return yearOfDeath - ageOfDeath;
    }
}
