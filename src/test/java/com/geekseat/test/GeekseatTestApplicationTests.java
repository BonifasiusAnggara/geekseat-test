package com.geekseat.test;

import com.geekseat.test.services.TheWitchStandService;
import com.geekseat.test.ui.model.PersonDto;
import com.geekseat.test.util.http.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeekseatTestApplicationTests {

    @Autowired
    private TheWitchStandService theWitchStandService;

    @Test
    void fibonacciNumber() {
        // fibonacci number less than 1 return the number itself

        int testZero = theWitchStandService.fibonacciNumber(0);
        int testOne = theWitchStandService.fibonacciNumber(1);

        assertEquals(0, testZero);
        assertEquals(1, testOne);

        // The Third Number of fibonacci is 1 (in loop, third number is considered index 2)
        int thirdNumber = theWitchStandService.fibonacciNumber(2);
        assertEquals(1, testOne);

        // The sixth Number of fibonacci is 5 as in 0 1 1 2 3 5
        int sixthNumber = theWitchStandService.fibonacciNumber(5);
        assertEquals(5, sixthNumber);

        // Collecting fibonacci sequence for 6 times should be [0, 1, 1, 2, 3, 5]
        int[] sequence = new int[6];
        for (int i = 0; i <= 5; i++) {
            sequence[i] = theWitchStandService.fibonacciNumber(i);
        }
        assertArrayEquals(new int[]{0, 1, 1, 2, 3, 5}, sequence);
    }

    @Test
    void numberOfKills() {
        // If inserted year is less than 0 it will return BadRequestException("The witch not take control yet")
        Exception exception = assertThrows(BadRequestException.class, () -> {
            theWitchStandService.numberOfKills(-1);
        });

        assertEquals(exception.getMessage(), "The witch is not take control yet");

        Exception exception2 = assertThrows(BadRequestException.class, () -> {
            theWitchStandService.numberOfKills(-1998);
        });

        assertEquals(exception2.getMessage(), "The witch is not take control yet");

        // For Consequent year of 1 to 5, numberOfKills is 1, 2, 4, 7, 12
        assertEquals(1, theWitchStandService.numberOfKills(1));
        assertEquals(2, theWitchStandService.numberOfKills(2));
        assertEquals(4, theWitchStandService.numberOfKills(3));
        assertEquals(7, theWitchStandService.numberOfKills(4));
        assertEquals(12, theWitchStandService.numberOfKills(5));
    }

    @Test
    void deathAverage() {
        // For two person as in questions, the average should be 4.5
        List<PersonDto> persons = Arrays.asList(
                PersonDto.builder()
                        .ageOfDeath(10)
                        .yearOfDeath(12)
                        .build(),
                PersonDto.builder()
                        .ageOfDeath(13)
                        .yearOfDeath(17)
                        .build()
        );

        assertEquals(4.5, theWitchStandService.getDeathAverage(persons));

        // Add for year 5 of death = 12, so the average of 2 7 12 will be 7
        List<PersonDto> anotherPersons = Arrays.asList(
                PersonDto.builder()
                        .ageOfDeath(14)
                        .yearOfDeath(16)
                        .build(), // year of death 2, numberOfKills 2
                PersonDto.builder()
                        .ageOfDeath(10)
                        .yearOfDeath(14)
                        .build(), // year of death 4, numberOfKills 7
                PersonDto.builder()
                        .ageOfDeath(16)
                        .yearOfDeath(21)
                        .build() // year of death 5, numberOfKills 12
        );

        assertEquals(7, theWitchStandService.getDeathAverage(anotherPersons));
    }

}
