package aws;

import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

public class create_ec2 {
    public static void main(String[] args) {
        try{
            Ec2Client ec2Client = Ec2Client.builder().build();
            String instanceProfileName = "MyNewJavaProfile";
            String amiId = "ami-051f8a213df8bc089";
            String instanceType = "t2.micro";

            RunInstancesRequest request = RunInstancesRequest.builder()
                    .imageId(amiId)
                    .instanceType(InstanceType.fromValue(instanceType))
                    .minCount(1)
                    .maxCount(1)
                    .iamInstanceProfile(IamInstanceProfileSpecification.builder()
                            .name(instanceProfileName).build())
                    .build();

            RunInstancesResponse response = ec2Client.runInstances(request);
            Instance instance = response.instances().get(0);
            String instanceId = instance.instanceId();
            System.out.println("EC2 with IAM Instance Profile "+ instanceProfileName + " launched Instance ID: "+instanceId);
            ec2Client.close();
        }catch(Ec2Exception e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
