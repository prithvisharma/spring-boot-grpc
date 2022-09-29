package com.demo.handler;

import com.demo.User;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHandler {

    public User handleUpdateUser(User newUser, User dbUser) {
        if (!newUser.getName().isEmpty()) {
            return User.newBuilder(dbUser).setName(newUser.getName()).build();
        }
        if (!(newUser.getAge() == 0)) {
            return User.newBuilder(dbUser).setAge(newUser.getAge()).build();
        }
        if (!newUser.getGender().isEmpty()) {
            return User.newBuilder(dbUser).setGender(newUser.getGender()).build();
        }
        if (!(newUser.getPhone() == 0)) {
            return User.newBuilder(dbUser).setPhone(newUser.getPhone()).build();
        }
        if (!newUser.getEmail().isEmpty()) {
            return User.newBuilder(dbUser).setEmail(newUser.getEmail()).build();
        }
        if (!newUser.getAddress().isEmpty()) {
            return User.newBuilder(dbUser).setAddress(newUser.getAddress()).build();
        }
        if (!newUser.getCity().isEmpty()) {
            return User.newBuilder(dbUser).setCity(newUser.getCity()).build();
        }
        if (!newUser.getState().isEmpty()) {
            return User.newBuilder(dbUser).setState(newUser.getState()).build();
        }
        if (!(newUser.getPincode() == 0)) {
            return User.newBuilder(dbUser).setPincode(newUser.getPincode()).build();
        }
        return dbUser;
    }
}
