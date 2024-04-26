package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Map;

public class RetrieveData {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();
        try{
            //using Query operation
            QueryRequest queryRequest = QueryRequest.builder()
                    .tableName("UserTable")
                    .keyConditionExpression("UserID = :userId")
                    .expressionAttributeValues(Map.of(":userId", AttributeValue.builder().s("user123").build()))
                    .build();

            QueryResponse response = client.query(queryRequest);
            System.out.println("Query Response: " );
            response.items().forEach(item -> System.out.println(item));

            //Using get operation

            GetItemRequest request = GetItemRequest.builder()
                    .tableName("UserTable")
                    .key(Map.of("UserID", AttributeValue.builder().s("user234").build()))
                    .build();

            GetItemResponse response1 = client.getItem(request);
            System.out.println("Get Result: ");
            System.out.println(response1.item());

        }catch (DynamoDbException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
