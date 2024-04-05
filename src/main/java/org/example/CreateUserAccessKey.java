package org.example;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateAccessKeyRequest;
import software.amazon.awssdk.services.iam.model.CreateAccessKeyResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class CreateUserAccessKey {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String username ="JavaUpdated";
            CreateAccessKeyRequest request = CreateAccessKeyRequest.builder()
                    .userName(username)
                    .build();
            CreateAccessKeyResponse response = iam.createAccessKey(request);
            System.out.println("Access key created for user: "+username);
            System.out.println("Access key ID: "+ response.accessKey().accessKeyId());
            System.out.println("Secret key : "+ response.accessKey().secretAccessKey());
            iam.close();
        } catch(IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
