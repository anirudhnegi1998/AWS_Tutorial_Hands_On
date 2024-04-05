package org.example;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.AttachUserPolicyRequest;
import software.amazon.awssdk.services.iam.model.AttachUserPolicyResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class AttachPolicy {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();

            String userName = "JavaUpdated";
            String policyArn = "arn:aws:iam::300993956176:policy/JavaNewPolicy";
            AttachUserPolicyRequest request = AttachUserPolicyRequest.builder()
                    .userName(userName)
                    .policyArn(policyArn)
                    .build();

            AttachUserPolicyResponse response = iam.attachUserPolicy(request);
            System.out.println("Policy attached to :"+ userName);
        }catch (IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
