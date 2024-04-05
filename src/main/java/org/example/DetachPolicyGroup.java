package org.example;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.DetachGroupPolicyRequest;
import software.amazon.awssdk.services.iam.model.IamException;

public class DetachPolicyGroup {
    public static void main(String[] args) {
        try {
            IamClient iam = IamClient.builder().build();
            String groupname = "MyNewGroup";
            String policy_arn = "arn:aws:iam::300993956176:policy/JavaNewPolicy";
            String policy = "JavaNewPolicy";
            DetachGroupPolicyRequest request = DetachGroupPolicyRequest.builder()
                    .groupName(groupname)
                    .policyArn(policy_arn)
                    .build();
            iam.detachGroupPolicy(request);
            System.out.println("Policy: "+policy+" detached from group: "+groupname);
            iam.close();

        } catch(IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
