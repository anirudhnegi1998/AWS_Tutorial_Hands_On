package org.example_IAM;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateLoginProfileRequest;
import software.amazon.awssdk.services.iam.model.CreateLoginProfileResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class CreateLoginProfile {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String username = "JavaUpdated";
            String password = "Password123@";
            CreateLoginProfileRequest request = CreateLoginProfileRequest.builder()
                    .userName(username)
                    .password(password)
                    .passwordResetRequired(false)
                    .build();
            CreateLoginProfileResponse response = iam.createLoginProfile(request);
            System.out.println("Login profile created for user :"+ username);
            iam.close();
        }catch(IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
