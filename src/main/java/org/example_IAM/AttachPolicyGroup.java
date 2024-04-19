package org.example_IAM;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.AttachGroupPolicyRequest;
import software.amazon.awssdk.services.iam.model.IamException;

public class AttachPolicyGroup {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String group = "MyNewGroup";
            String policyname = "JavaPolicy";
            String policy_arn = "arn:aws:iam::300993956176:policy/JavaNewPolicy";
            AttachGroupPolicyRequest request = AttachGroupPolicyRequest.builder()
                    .groupName(group)
                    .policyArn(policy_arn)
                    .build();
            iam.attachGroupPolicy(request);
            System.out.println("Attached policy:"+ policyname+" to group :"+ group);
            iam.close();
        }catch (IamException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
