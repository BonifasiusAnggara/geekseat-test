package com.geekseat.test.services;

import com.geekseat.test.ui.model.PersonDto;
import com.geekseat.test.ui.model.PersonsDto;

import java.math.BigInteger;
import java.util.List;

public interface TheWitchStandService {
    public double getDeathAverage(List<PersonDto> persons);
    public Long numberOfKills(int year);
    public Long fibonacciNumber(int n);
}
