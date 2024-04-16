package aws;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.Tag;
import software.amazon.awssdk.services.iam.model.TagUserRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

public class AddTagtoUser {
    public static void main(String[] args) {
        IamClient iam = IamClient.builder().build();
        String username = "d";
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.builder().key("Department").value("HR").build());
        tags.add(Tag.builder().key("Project").value("Onboarding").build());
        try{
        TagUserRequest request = TagUserRequest.builder()
                .userName(username)
                .tags(tags)
                .build();

            iam.tagUser(request);
            System.out.println("Added tag to user: "+ username);
        }catch (IamException e){
            e.awsErrorDetails().errorMessage();
        }
    }
}
