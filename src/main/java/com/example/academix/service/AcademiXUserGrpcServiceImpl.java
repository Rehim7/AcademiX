package com.example.academix.service;

import com.example.academix.dto.response.AcademiXUserResponse;
import com.example.academix.myEnums.Roles;
import com.example.academix.proto.AcademiXUserServiceGrpc;
import com.example.academix.proto.CreateAcademiXUserRequest;
import com.example.academix.proto.CreateAcademiXUserResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AcademiXUserGrpcServiceImpl extends AcademiXUserServiceGrpc.AcademiXUserServiceImplBase {

    private final AcademiXUserService userService;

    public AcademiXUserGrpcServiceImpl(AcademiXUserService userService) {
        this.userService = userService;
    }

    @Override
    public void createAcademiXUser(CreateAcademiXUserRequest request, StreamObserver<CreateAcademiXUserResponse> responseObserver) {
        try {
            Roles roleEnum;
            switch (request.getRole()) {
                case STUDENT:
                    roleEnum = Roles.STUDENT;
                    break;
                case TEACHER:
                    roleEnum = Roles.TEACHER;
                    break;
                default:
                    roleEnum = Roles.STUDENT; // Default fallback
                    break;
            }

            AcademiXUserResponse userResponse = userService.createUser(
                    request.getName(),
                    request.getSurName(),
                    request.getEmail(),
                    request.getPassword(),
                    roleEnum,
                    request.getId()
            );

            CreateAcademiXUserResponse response = CreateAcademiXUserResponse.newBuilder()
                    .setSuccess(true)
                    .setUuid(userResponse.getUUID())
                    .setMessage("User created successfully")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            CreateAcademiXUserResponse response = CreateAcademiXUserResponse.newBuilder()
                    .setSuccess(false)
                    .setUuid("")
                    .setMessage(e.getMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
