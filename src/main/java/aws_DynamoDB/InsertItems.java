package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;

import java.util.HashMap;
import java.util.Map;

public class InsertItems {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();
        try{
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("UserID",AttributeValue.builder().s("user234").build());
            item.put("FirstName",AttributeValue.builder().s("Anirudh").build());
            item.put("LastName",AttributeValue.builder().s("negi").build());
            item.put("Email",AttributeValue.builder().s("negi@gmail.com").build());

            PutItemRequest putItemRequest = PutItemRequest.builder()
                    .tableName("UserTable")
                    .item(item)
                    .build();

            PutItemResponse response = client.putItem(putItemRequest);
            String requestID = response.responseMetadata().requestId();
            System.out.println("Request ID: " + requestID);
        }catch(DynamoDbException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }finally {
            client.close();
        }
    }
}
