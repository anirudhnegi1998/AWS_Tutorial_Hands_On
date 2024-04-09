package aws;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreatePolicyRequest;
import software.amazon.awssdk.services.iam.model.CreatePolicyResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class CreatePolicy {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String role = "MyNewJavaRole";
            String policyName = "MyNewJavaPolicy";
            String policyDocument = """
                    {
                        "Version": "2012-10-17",
                        "Statement": [
                            {
                                "Effect": "Allow",
                                "Action": [
                                    "s3:Get*",
                                    "s3:List*",
                                    "s3:Describe*",
                                    "s3-object-lambda:Get*",
                                    "s3-object-lambda:List*"
                                ],
                                "Resource": "arn:aws:s3:::myawsbucket123z"
                            }
                        ]
                    }""";
            CreatePolicyRequest request = CreatePolicyRequest.builder()
                    .policyDocument(policyDocument)
                    .policyName(policyName)
                    .description("My S3 read only policy")
                    .build();
            CreatePolicyResponse response = iam.createPolicy(request);
            System.out.println("Created Policy :"+policyName);
            iam.close();
        }catch(IamException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
