package com.geekseat.test.ui.model;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
                ", witchYear=" + getWitchYear() +
                '}';
    }

    public int getWitchYear() {
        if (yearOfDeath < 0 || ageOfDeath < 0) {
            return -1;
        }

        int witchYear = yearOfDeath - ageOfDeath;

        if (witchYear < 0) {
            return -1;
        }

        return witchYear;
    }

}
