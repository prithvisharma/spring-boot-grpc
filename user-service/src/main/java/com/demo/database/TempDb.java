package com.demo.database;

import com.demo.User;

import java.util.ArrayList;
import java.util.List;

public class TempDb {
    public static List<User> getUsersFromTempDb() {
        return new ArrayList<User>() {
            {
                add(User.newBuilder().setId("U101").setName("Prithvi Sharma").setAge(23).setGender("MALE").setPhone(9096410286l)
                        .setEmail("prithvi2255@gmail.com").setAddress("Nalanda, Naigaon East").setCity("Mumbai").setState("Maharashtra")
                        .setPincode(401208).build());
                add(User.newBuilder().setId("U102").setName("John Doe").setAge(33).setGender("MALE").setPhone(92232954532l)
                        .setEmail("johndoe@gmail.com").setAddress("Gokuldham, Goregaon East").setCity("Mumbai").setState("Maharashtra")
                        .setPincode(400063).build());
            }
        };
    }
}
