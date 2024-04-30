package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class CreateBackup {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();
        try{
            String tableName = "UserTable";
            CreateBackupRequest backupRequest = CreateBackupRequest.builder()
                    .tableName(tableName)
                    .backupName("MyBackup")
                    .build();
            CreateBackupResponse response = client.createBackup(backupRequest);
            System.out.println("Backup created successfully: "+ response.backupDetails().backupName());
        }catch(DynamoDbException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }finally {
            client.close();
        }
    }
}
