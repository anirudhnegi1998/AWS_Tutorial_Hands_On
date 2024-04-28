package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemResponse;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.Collections;

public class DeleteItem {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();

        try{
            DeleteItemRequest request = DeleteItemRequest.builder()
                    .tableName("UserTable")
                    .key(Collections.singletonMap("UserID", AttributeValue.builder()
                                    .s("user123")
                            .build()))
                    .build();

            DeleteItemResponse response = client.deleteItem(request);
            System.out.println("Item deleted "+ response );
        }catch(DynamoDbException e){
            e.awsErrorDetails().errorMessage();
        }finally {
            client.close();
        }
    }
}
