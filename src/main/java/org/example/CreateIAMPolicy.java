package org.example;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreatePolicyRequest;
import software.amazon.awssdk.services.iam.model.CreatePolicyResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class CreateIAMPolicy {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String customPolicy = """
                    {
                        "Version": "2012-10-17",
                        "Statement": [
                            {
                                "Effect": "Allow",
                                "Action": [
                                    "s3:ListBucket",
                                    "s3:ListAllMyBuckets"
                                ],
                                "Resource": "arn:aws:s3:::*"
                            }
                        ]
                    }""";
            CreatePolicyRequest request = CreatePolicyRequest.builder()
                            .policyName("JavaNewPolicy")
                            .policyDocument(customPolicy)
                            .description("My custom Policy for listing only").build();

            CreatePolicyResponse response = iam.createPolicy(request);
            System.out.println("Custom policy is created with ARN:" + response.policy().arn());
            iam.close();
        } catch (IamException e){
            System.out.println(e.awsErrorDetails().errorMessage());
        }
    }
}
