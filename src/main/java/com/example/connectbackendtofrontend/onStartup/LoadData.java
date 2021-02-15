package com.example.connectbackendtofrontend.onStartup;

import com.example.connectbackendtofrontend.fakedata.FakeData;
import com.example.connectbackendtofrontend.fakedata.FakeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements CommandLineRunner {

    @Autowired
    private FakeDataService fakeDataService;

    @Override
    public void run(String... args) throws Exception {
        FakeData user1 = new FakeData("John", "Doe", "USA", null);
        fakeDataService.addUser(user1);
//        fakeDataService.addUser(user2);
    }
}
