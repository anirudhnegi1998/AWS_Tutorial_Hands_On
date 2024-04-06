package org.example;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;

public class DeleteIAMUser {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String username = "JavaUpdated";
            String group = "MyNewGroup";
            String accessKey = "AKIAUMFFDJFIIU326DV2";
            //In order to delete a user from IAM, you need to first delete the login profile or any other resource for that user
            DeleteLoginProfileRequest request_delete_profile = DeleteLoginProfileRequest.builder()
                    .userName(username)
                    .build();
            iam.deleteLoginProfile(request_delete_profile);

            RemoveUserFromGroupRequest request_group = RemoveUserFromGroupRequest.builder()
                    .userName(username)
                    .groupName(group)
                    .build();
            iam.removeUserFromGroup(request_group);

            DeleteAccessKeyRequest request_keys = DeleteAccessKeyRequest.builder()
                    .userName(username)
                    .accessKeyId(accessKey)
                    .build();
            iam.deleteAccessKey(request_keys);

            DeleteUserRequest request = DeleteUserRequest.builder()
                    .userName(username)
                    .build();
            iam.deleteUser(request);
            iam.close();
            System.out.println("Deleted User: "+ username);
        }catch(IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
