package com.example.connectbackendtofrontend.controllers;

import com.example.connectbackendtofrontend.fakedata.FakeData;
import com.example.connectbackendtofrontend.fakedata.FakeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
@RestController
@CrossOrigin("https://front-end-example.azurewebsites.net")
public class FakeDataController {

    @Autowired
    private FakeDataService fakeDataService;

    @GetMapping({"", "/"})
    public String hello() {
        return "Hello";
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/fakeUsers")
    public List<FakeData> getFakeUsers() {
//        log.debug("Fake Users: " + fakeDataService.getFakeUsers());
        return fakeDataService.getFakeUsers();
    }

    @PostMapping("/fakeUsers/{userId}/update")
    public void addUserPronouns(@PathVariable String userId, @RequestParam String pronouns) {
        fakeDataService.addUserPronouns(Long.valueOf(userId), pronouns);

    }
}
