package com.demo.service;

import com.demo.User;
import com.demo.UserServiceGrpc;
import com.google.protobuf.Descriptors;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApiService {

    @GrpcClient("grpc-client")
    private UserServiceGrpc.UserServiceBlockingStub userGrpcClient;

    public Map<Descriptors.FieldDescriptor, Object> getUser(String id){
        final User user = User.newBuilder().setId(id).build();
        final User grpcResponse = userGrpcClient.getUser(user);
        return grpcResponse.getAllFields();
    }
}
