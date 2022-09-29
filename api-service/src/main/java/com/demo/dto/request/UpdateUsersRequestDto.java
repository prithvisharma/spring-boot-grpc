package com.demo.dto.request;

import com.demo.User;

public class UpdateUsersRequestDto {
    private String id;
    private String field;
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User generateUser(){
        switch (this.getField().toUpperCase()){
            case "NAME":
                return User.newBuilder().setId(this.getId()).setName(this.value).build();
            case "AGE":
                return User.newBuilder().setId(this.getId()).setAge(Integer.parseInt(this.value)).build();
            case "GENDER":
                return User.newBuilder().setId(this.getId()).setGender(this.value).build();
            case "PHONE":
                return User.newBuilder().setId(this.getId()).setPhone(Long.parseLong(this.value)).build();
            case "EMAIL":
                return User.newBuilder().setId(this.getId()).setEmail(this.value).build();
            case "ADDRESS":
                return User.newBuilder().setId(this.getId()).setAddress(this.value).build();
            case "CITY":
                return User.newBuilder().setId(this.getId()).setCity(this.value).build();
            case "STATE":
                return User.newBuilder().setId(this.getId()).setState(this.value).build();
            case "PINCODE":
                return User.newBuilder().setId(this.getId()).setPincode(Integer.parseInt(this.value)).build();
            default:
                throw new RuntimeException("invalid field");
        }
    }
}
