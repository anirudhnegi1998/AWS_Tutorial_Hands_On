package org.example;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.ListUsersRequest;
import software.amazon.awssdk.services.iam.model.ListUsersResponse;
import software.amazon.awssdk.services.iam.model.User;

import java.util.List;

public class ListUsers {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            ListUsersRequest request = ListUsersRequest.builder().build();
            ListUsersResponse response = iam.listUsers(request);
            List<User> users = response.users();
            for (User user: users) {
                System.out.println(user.userName());
            }
            iam.close();
        } catch(IamException e) {
            System.err.println("Error : "+ e.awsErrorDetails().errorMessage() );
        }
    }
}
