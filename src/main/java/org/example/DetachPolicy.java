package org.example;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.DetachUserPolicyRequest;
import software.amazon.awssdk.services.iam.model.IamException;

public class DetachPolicy {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String username = "JavaUpdated";
            String policy = "arn:aws:iam::300993956176:policy/JavaNewPolicy";
            DetachUserPolicyRequest request = DetachUserPolicyRequest.builder()
                    .userName(username)
                    .policyArn(policy)
                    .build();
            iam.detachUserPolicy(request);
            System.out.println("Detached User Policy for user: "+ username);
        }catch (IamException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
