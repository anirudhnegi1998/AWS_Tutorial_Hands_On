package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.util.Map;

public class QueryCondition {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();
        try{
            QueryRequest request = QueryRequest.builder()
                    .tableName("UserTable")
                    .keyConditionExpression("UserID = :userID")
                    .filterExpression("Age > :ageLimit")
                    .expressionAttributeValues(Map.of(
                            ":userID", AttributeValue.builder().s("user123").build(),
                            ":ageLimit", AttributeValue.builder().n("15").build()
                    )).build();

            QueryResponse response = client.query(request);
            System.out.println("QueryResponse with conditions: " + response);
            for (Map<String, AttributeValue> item : response.items()) {
                System.out.println("Item attributes: ");
                for(Map.Entry<String, AttributeValue> entry: item.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
            System.out.println();
        }catch(DynamoDbException e){
            System.err.println(e.awsErrorDetails()
                    .errorMessage());
        }finally {
            client.close();
        }
    }
}
