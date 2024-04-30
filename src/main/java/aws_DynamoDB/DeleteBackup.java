package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class DeleteBackup {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();
        try{
            String tableArn = "arn:aws:dynamodb:us-east-1:300993956176:table/UserTable/backup/01714425803768-24cea113";
            DeleteBackupRequest request = DeleteBackupRequest.builder()
                    .backupArn(tableArn)
                    .build();
            client.deleteBackup(request);
            System.out.println("Backup deletion initialted");
        }catch(
                DynamoDbException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }finally {
            client.close();
        }
    }

}
