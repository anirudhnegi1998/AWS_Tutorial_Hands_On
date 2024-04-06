package org.example;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.DeleteGroupRequest;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.RemoveUserFromGroupRequest;

public class DeleteIAMGroup {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String groupName = "MyNewGroup";
            String user = "NewUser";

            RemoveUserFromGroupRequest request_user = RemoveUserFromGroupRequest.builder()
                    .groupName(groupName)
                    .userName(user)
                    .build();
            iam.removeUserFromGroup(request_user);

            DeleteGroupRequest request = DeleteGroupRequest.builder()
                    .groupName(groupName)
                    .build();
            iam.deleteGroup(request);
            iam.close();
            System.out.println("Group deleted :"+ groupName);
        }catch (IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
