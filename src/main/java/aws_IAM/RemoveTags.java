package aws_IAM;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.UntagUserRequest;

import java.util.List;
import java.util.ArrayList;

public class RemoveTags {
    public static void main(String[] args) {
        IamClient iam = IamClient.builder().build();
        String username = "NewUser"; //user from which we will remove tags
        List<String> tagkeys = new ArrayList<>();

        tagkeys.add("Department");
        tagkeys.add("Project");

        UntagUserRequest untagUserRequest = UntagUserRequest.builder()
                .userName(username)
                .tagKeys(tagkeys)
                .build();

        try{
            iam.untagUser(untagUserRequest);
            System.out.println("Untagged user successfully: "+ username);
            iam.close();
        }catch(IamException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }

    }
}
