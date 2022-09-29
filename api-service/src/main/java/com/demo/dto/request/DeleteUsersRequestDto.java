package com.demo.dto.request;

import com.demo.DeleteRequest;
import com.demo.User;

public class DeleteUsersRequestDto {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User generateUser() {
        return User.newBuilder().setId(this.id).build();
    }

    public DeleteRequest generateDeleteRequest() {
        return DeleteRequest.newBuilder().setId(this.getId()).build();
    }
}
