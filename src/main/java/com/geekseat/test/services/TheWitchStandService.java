package com.geekseat.test.services;

import com.geekseat.test.ui.model.PersonDto;
import com.geekseat.test.ui.model.PersonsDto;

import java.util.List;

public interface TheWitchStandService {
    public double getDeathAverage(List<PersonDto> persons);
    public int numberOfKills(int year);
    public int fibonacciNumber(int n);
}
