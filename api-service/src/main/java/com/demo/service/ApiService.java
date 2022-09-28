package com.demo.service;

import com.demo.User;
import com.demo.UserServiceGrpc;
import com.google.protobuf.Descriptors;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class ApiService {

    @GrpcClient("grpc-client")
    private UserServiceGrpc.UserServiceBlockingStub userGrpcClient;

    @GrpcClient("grpc-client")
    private UserServiceGrpc.UserServiceStub userGrpcStreamingClient;

    public Map<Descriptors.FieldDescriptor, Object> getUser(String id){
        final User user = User.newBuilder().setId(id).build();
        final User grpcResponse = userGrpcClient.getUser(user);
        return grpcResponse.getAllFields();
    }

    public List<Map<Descriptors.FieldDescriptor, Object>> getAlikeUsers(String nameLike) throws InterruptedException {
        final User user = User.newBuilder().setName(nameLike).build();
        final List<Map<Descriptors.FieldDescriptor, Object>> response = new ArrayList<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        userGrpcStreamingClient.getAlikeUser(user, new StreamObserver<User>() {
            @Override
            public void onNext(User value) {
                response.add(value.getAllFields());
            }

            @Override
            public void onError(Throwable t) {
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });

        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptyList();
    }
}
