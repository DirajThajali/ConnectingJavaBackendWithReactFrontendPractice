package com.example.connectbackendtofrontend.fakedata;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FakeDataService {

    private List<FakeData> fakeUsers = new ArrayList<>();

    private Map<Long, FakeData> mapIdToUser = new HashMap<>();

    public List<FakeData> getFakeUsers() {
        return fakeUsers;
    }

    public void addUser(FakeData user) {
        fakeUsers.add(user);
        mapIdToUser.put(user.getId(), user);
    }

    public void addUserPronouns(long userId, String pronouns) {
        if (mapIdToUser.containsKey(userId)) {
            mapIdToUser.get(userId).setPronouns(pronouns);
        }
    }
}
