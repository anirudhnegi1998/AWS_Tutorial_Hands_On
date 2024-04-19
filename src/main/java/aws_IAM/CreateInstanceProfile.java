package aws_IAM;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateInstanceProfileRequest;
import software.amazon.awssdk.services.iam.model.CreateInstanceProfileResponse;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.InstanceProfile;

public class CreateInstanceProfile {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String instanceProfileName = "MyNewJavaProfile";

            CreateInstanceProfileRequest request = CreateInstanceProfileRequest.builder()
                    .instanceProfileName(instanceProfileName)
                    .build();

            CreateInstanceProfileResponse response = iam.createInstanceProfile(request);
            InstanceProfile createdInstanceProfile = response.instanceProfile();
            System.out.println("IAM instance profile created: "+ createdInstanceProfile.instanceProfileName());
            iam.close();
        }catch(IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());

        }
    }
}
