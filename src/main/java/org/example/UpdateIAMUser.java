package org.example;
import software.amazon.awssdk.services.iam.model.UpdateUserRequest;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;

public class UpdateIAMUser {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            UpdateUserRequest request = UpdateUserRequest.builder().userName("JavaUser")
                    .newUserName("JavaUpdated")
                    .build();
            iam.updateUser(request);
            System.out.println("User updated Successfully");
            iam.close();
        } catch(IamException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
