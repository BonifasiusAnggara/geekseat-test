package com.geekseat.test.services.impl;

import com.geekseat.test.services.TheWitchStandService;
import com.geekseat.test.ui.model.PersonsDto;
import com.geekseat.test.util.http.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TheWitchStandServiceImpl implements TheWitchStandService {

    @Override
    public double getDeathAverage(PersonsDto persons) {
        if (persons.getPerson1().getWitchYear() < 0 || persons.getPerson2().getWitchYear() < 0) {
            return -1.00;
        }

        double numberOfKills1 = numberOfKills(persons.getPerson1().getWitchYear());
        double numberOfKills2 = numberOfKills(persons.getPerson2().getWitchYear());

        return (numberOfKills1 + numberOfKills2) / 2;
    }

    private static int numberOfKills(int year) {
        if (year < 0) {
            throw new BadRequestException("The witch not take control yet");
        }

        if (year <= 1) {
            return year;
        }

        List<Integer> fibonacciSequence = fibonacciSequence(year);

        return fibonacciSequence.stream().reduce(0, Integer::sum);
    }

    private static List<Integer> fibonacciSequence(int seq) {
        List<Integer> sequence = new ArrayList<>();

        int num1 = 0, num2 = 1;
        int counter = 0;

        sequence.add(num1, num2);

        // Iterate till counter is N
        while (counter < seq -1) {
            // Swap
            int num3 = num2 + num1;
            sequence.add(num3);
            num1 = num2;
            num2 = num3;
            counter = counter + 1;
        }

        return sequence;
    }
}
