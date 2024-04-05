package org.example;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.AddUserToGroupRequest;
import software.amazon.awssdk.services.iam.model.AddUserToGroupResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class AddUsersGroup {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String groupname = "MyNewGroup";
            String[] userNames = {"JavaUpdated","NewUser"};
            for(String userName:userNames) {
                AddUserToGroupRequest request = AddUserToGroupRequest.builder()
                        .groupName(groupname)
                        .userName(userName)
                        .build();
                iam.addUserToGroup(request);
                System.out.println("User :"+userName+" added to group: "+groupname);
            }
            iam.close();
        }catch (IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
