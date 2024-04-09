package aws;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.AttachRolePolicyRequest;
import software.amazon.awssdk.services.iam.model.AttachRolePolicyResponse;
import software.amazon.awssdk.services.iam.model.AttachUserPolicyResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class AttachPolicyToRole {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String policyArn = "arn:aws:iam::300993956176:policy/MyNewJavaPolicy";
            String role = "MyNewJavaRole";

            AttachRolePolicyRequest request = AttachRolePolicyRequest.builder()
                    .roleName(role)
                    .policyArn(policyArn)
                    .build();

            AttachRolePolicyResponse response = iam.attachRolePolicy(request);
            System.out.println("Attached policy : "+ policyArn+ " attached to IAM role: "+ role);
            iam.close();
        }catch(IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
