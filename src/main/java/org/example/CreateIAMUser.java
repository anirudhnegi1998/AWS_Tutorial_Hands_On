package org.example;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateUserRequest;
import software.amazon.awssdk.services.iam.model.CreateUserResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class CreateIAMUser {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            CreateUserRequest request = CreateUserRequest.builder().userName("NewUser").build();
            CreateUserResponse response = iam.createUser(request);
            System.out.println(response);
        } catch(IamException e){
            System.err.println((e.awsErrorDetails().errorMessage()));
        }
    }
}