package com.example.demography.controllers;

import com.example.demography.dto.CountMessageDto;
import com.example.demography.dto.PercentageMassageDto;
import org.springframework.web.bind.annotation.*;
import ru.lab2.library.Nationality;
import ru.lab2.library.Color;
import com.example.demography.service.PersonService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("demography")
@RequiredArgsConstructor
public class DemographyController {
    private final PersonService personService;

    @GetMapping("{eyeColor}/percentage")
    public PercentageMassageDto getPercentage(@PathVariable  Color eyeColor){
        System.out.println(eyeColor);
        return new PercentageMassageDto(personService.getPercentageEyeColor(eyeColor));
    }

    @GetMapping("nationality/{nationality}/eye-color/{eyeColor}")
    public CountMessageDto getCountPersons(@PathVariable Nationality nationality, @PathVariable Color eyeColor){
        System.out.println(nationality + " " + eyeColor);
        return new CountMessageDto(personService.getCountByEyeColorAndNationality(eyeColor, nationality));
    }
}
