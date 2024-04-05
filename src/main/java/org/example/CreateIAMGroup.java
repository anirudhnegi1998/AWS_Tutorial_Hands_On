package org.example;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateGroupRequest;
import software.amazon.awssdk.services.iam.model.CreateGroupResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class CreateIAMGroup {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String groupname = "MyNewGroup";
            CreateGroupRequest request = CreateGroupRequest.builder()
                    .groupName(groupname)
                    .build();
            CreateGroupResponse response = iam.createGroup(request);
            System.out.println("Group created : "+ groupname + " "+ response.group().arn());
        }catch (IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
