package aws;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;

import java.util.List;

public class ListTag {
    public static void main(String[] args) {
        IamClient iam = IamClient.builder().build();
        try{
            ListUsersRequest listUsersRequest = ListUsersRequest.builder().build();
            ListUsersResponse listUsersResponse = iam.listUsers(listUsersRequest);

            for(User user : listUsersResponse.users()) {
                //System.out.println(user); print it in order to check all the details of user like USerID, Arn
                String userName = user.userName();
                ListUserTagsRequest listUserTagsRequest = ListUserTagsRequest.builder().userName(userName).build();
                ListUserTagsResponse listUserTagsResponse = iam.listUserTags(listUserTagsRequest);

                List<Tag> tags = listUserTagsResponse.tags();
                System.out.println("User : " + userName+ " Tags : " + tags.size());

                if(tags.size() > 0){
                    for(Tag tag : tags){
                        System.out.println(tag.key() + " : " + tag.value());
                    }
                    System.out.println();
                } else {
                    System.out.println("No tags found");
                }
            }
        }catch (IamException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
