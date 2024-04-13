package aws;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.AddRoleToInstanceProfileRequest;
import software.amazon.awssdk.services.iam.model.AddRoleToInstanceProfileResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class AddRuleInstanceProfile {
    public static void main(String[] args) {

        try {
            IamClient iam = IamClient.builder().build();
            String rolename = "MyNewJavaRole";
            String instanceProfileName = "MyNewJavaProfile";

            AddRoleToInstanceProfileRequest request = AddRoleToInstanceProfileRequest.builder()
                    .instanceProfileName(instanceProfileName)
                    .roleName(rolename)
                    .build();

            AddRoleToInstanceProfileResponse response = iam.addRoleToInstanceProfile(request);
            System.out.println("Added role :"+rolename + " to instance profile: "+ instanceProfileName);

        }catch(IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}

