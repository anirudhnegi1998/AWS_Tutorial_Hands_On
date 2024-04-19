package aws_IAM;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateRoleRequest;
import software.amazon.awssdk.services.iam.model.CreateRoleResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class CreateRoles {
    public static void main(String[] args) {
        try{
            IamClient iam = IamClient.builder().build();
            String rolename = "MyNewJavaRole";
            //Add your service here which you want to assume role for
            String trust = """
                    {
                        "Version": "2012-10-17",
                        "Statement": [
                            {
                                "Effect": "Allow",
                                "Principal": {
                                    "Service": "ec2.amazonaws.com" 
                                },
                                "Action": "sts:AssumeRole"
                            }
                        ]
                    }""";
            CreateRoleRequest request = CreateRoleRequest.builder()
                    .roleName(rolename)
                    .assumeRolePolicyDocument(trust)
                    .description("New Java Role")
                    .build();

            CreateRoleResponse response = iam.createRole(request);
            System.out.println("The new Java Role created: "+ response.role().roleName());
            iam.close();
        } catch(IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
