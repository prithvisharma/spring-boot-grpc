package com.demo.service;

import com.demo.DeleteRequest;
import com.demo.DeleteResponse;
import com.demo.User;
import com.demo.UserServiceGrpc;
import com.demo.database.UserDatabase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    @Autowired
    private UserDatabase userDatabase;
    @Override
    public void getUser(User request, StreamObserver<User> responseObserver) {
        final User user = userDatabase.findById(request.getId());
        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

    @Override
    public void getAlikeUser(User request, StreamObserver<User> responseObserver) {
        final List<User> userList = userDatabase.findAlikeByName(request.getName());
        userList.forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<User> saveUsers(StreamObserver<User> responseObserver) {
        return new StreamObserver<User>() {
            final List<User> userList = new ArrayList<>();

            @Override
            public void onNext(User user) {
                final String id = userDatabase.insert(user);
                final User finalUser = User.newBuilder(user).setId(id).build();
                System.out.println(finalUser.getId());
                System.out.println(finalUser.getName());
                responseObserver.onNext(finalUser);
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                userList.forEach(responseObserver::onNext);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<User> updateUsers(StreamObserver<User> responseObserver) {
        return super.updateUsers(responseObserver);
    }

    @Override
    public StreamObserver<DeleteRequest> deleteUsers(StreamObserver<DeleteResponse> responseObserver) {
        return super.deleteUsers(responseObserver);
    }
}
