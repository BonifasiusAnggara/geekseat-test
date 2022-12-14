package com.geekseat.test.services.impl;

import com.geekseat.test.services.TheWitchStandService;
import com.geekseat.test.ui.model.PersonDto;
import com.geekseat.test.util.http.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TheWitchStandServiceImpl implements TheWitchStandService {

    @Override
    public double getDeathAverage(List<PersonDto> persons) {
        return persons.stream().mapToInt(person -> numberOfKills(person.getWitchYear())).average().orElse(0.0);
    }

    @Override
    public int numberOfKills(int year) {
        if (year < 0) {
            throw new BadRequestException("The witch is not take control yet");
        }

        List<Integer> fibonacciSequence = new ArrayList<>();
        for (int i = 0; i <= year; i++) {
            fibonacciSequence.add(fibonacciNumber(i));
        }

        return fibonacciSequence.stream().reduce(0, Integer::sum);
    }

    @Override
    public int fibonacciNumber(int n) {
        if (n <= 1)
            return n;

        return fibonacciNumber(n - 1) + fibonacciNumber(n - 2);
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
