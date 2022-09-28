package com.demo.service;

import com.demo.User;
import com.demo.UserServiceGrpc;
import com.demo.database.TempDb;
import com.demo.database.UserDatabase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

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
}
