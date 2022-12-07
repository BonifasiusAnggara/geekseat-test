package com.geekseat.test;

import com.geekseat.test.services.TheWitchStandService;
import com.geekseat.test.ui.model.PersonDto;
import com.geekseat.test.ui.model.PersonsDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GeekseatTestApplicationTests {

    @Autowired
    private TheWitchStandService theWitchStandService;

    PersonDto person1 = new PersonDto();
    PersonDto person2 = new PersonDto();

    @Test
    void returnMinusOne() {
        person1.setAgeOfDeath(10);
        person1.setYearOfDeath(7);
        person2.setAgeOfDeath(13);
        person2.setYearOfDeath(17);
        PersonsDto persons = new PersonsDto(person1, person2);

        double deathAverage = theWitchStandService.getDeathAverage(persons);

        assertEquals(-1.00, deathAverage);
    }

    @Test
    void returnTheAverage() {
        person1.setAgeOfDeath(10);
        person1.setYearOfDeath(12);
        person2.setAgeOfDeath(13);
        person2.setYearOfDeath(17);
        PersonsDto persons = new PersonsDto(person1, person2);

        double deathAverage = theWitchStandService.getDeathAverage(persons);

        assertEquals(4.5, deathAverage);
    }

}
