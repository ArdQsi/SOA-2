package com.example.demography.service;

import ru.lab2.library.Person;
import ru.lab2.library.Color;
import ru.lab2.library.Nationality;
import com.example.demography.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
   private final PersonRepository personRepository;

   public Double getPercentageEyeColor(Color eyeColor) {
       double countPersonEye, countAllPerson;
       countPersonEye = personRepository.getByEyeColor(eyeColor).block().size();
       countAllPerson = personRepository.getByAll().block().size();
       return countPersonEye * 100 / countAllPerson;
   }

    public Integer getCountByEyeColorAndNationality(Color eyeColor, Nationality nationality) {
        List<Person> personList = personRepository.getByEyeColor(eyeColor).block();
        Integer count = 0;
        for(Person person : personList){
            if(person.getNationality()==nationality){
                count++;
            }
        }
        return count;
    }
}
