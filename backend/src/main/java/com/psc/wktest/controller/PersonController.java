package com.psc.wktest.controller;

import com.psc.wktest.model.ImcByAge;
import com.psc.wktest.model.MediaAge;
import com.psc.wktest.model.PercentageOfObese;
import com.psc.wktest.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/countByState")
    public ResponseEntity<List<Object[]>> countPersonsByState() {
        return new ResponseEntity<>(personService.countPersonsByState(), HttpStatus.OK);
    }

    @GetMapping("/imcByAge")
    public ResponseEntity<List<ImcByAge>> imcByAge() {
        return new ResponseEntity<>(personService.imcByAge(), HttpStatus.OK);
    }

    @GetMapping("/percentageOfObese")
    public ResponseEntity<List<PercentageOfObese>> percentageOfObese() {
        return new ResponseEntity<>(personService.percentageOfObese(), HttpStatus.OK);
    }

    @GetMapping("/mediaAge")
    public ResponseEntity<List<MediaAge>> mediaAge() {
        return new ResponseEntity<>(personService.mediaAge(), HttpStatus.OK);
    }

    @GetMapping("/possibleDonors")
    public ResponseEntity<Map<String, Integer>> possibleDonors() {
        return new ResponseEntity<>(personService.possibleDonors(), HttpStatus.OK);
    }
}
