package org.example_IAM;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreatePolicyRequest;
import software.amazon.awssdk.services.iam.model.CreatePolicyResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class CreateIAMPolicy {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String policyDocument = "{\n" +
                    "  \"Version\": \"2012-10-17\",\n" +
                    "  \"Statement\": [\n" +
                    "    {\n" +
                    "      \"Effect\": \"Allow\",\n" +
                    "      \"Action\": \"logs:CreateLogGroup\",\n" +
                    "      \"Resource\": \"arn:aws:logs:us-west-2:123456789012:*\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            CreatePolicyRequest request = CreatePolicyRequest.builder()
                    .policyName("NewPolicy")
                    .policyDocument(policyDocument)
                    .build();

            CreatePolicyResponse response = iam.createPolicy(request);
            System.out.println("Policy created: " + response.policy());

        }catch(IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
