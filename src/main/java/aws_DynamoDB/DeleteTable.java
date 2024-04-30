package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DeleteBackupRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableResponse;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class DeleteTable {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();
        try{
            String tableName = "UserTabel";
            DeleteTableRequest deleteTableRequest = DeleteTableRequest.builder().tableName(tableName).build();
            DeleteTableResponse deleteTableResponse = client.deleteTable(deleteTableRequest);
            System.out.println("Table " + tableName + " deleted "+ deleteTableResponse.tableDescription());
        }catch(
                DynamoDbException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }finally {
            client.close();
        }
    }
}
