package com.geekseat.test.ui.controller;

import com.geekseat.test.services.TheWitchStandService;
import com.geekseat.test.ui.model.PersonsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@Slf4j
@RestController
@RequestMapping("/witch")
public class TheWitchCraft {

    private final TheWitchStandService witchStandService;

    public TheWitchCraft(TheWitchStandService witchStandService) {
        this.witchStandService = witchStandService;
    }

    @PostMapping("/death-average")
    public double getAverageDeath(@Valid @RequestBody PersonsDto personsDto) {
        return witchStandService.getDeathAverage(personsDto.getPersons());
    }
}
