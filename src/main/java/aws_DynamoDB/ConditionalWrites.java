package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConditionalWrites {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();

        try{
           String conditionsExpression = "Age < :maxAge";
           Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
           expressionAttributeValues.put(":maxAge", AttributeValue.builder().n("40").build());
           expressionAttributeValues.put(":newAge", AttributeValue.builder().n("50").build());

            UpdateItemRequest request = UpdateItemRequest.builder()
                    .tableName("UserTable")
                    .key(Collections.singletonMap("UserID", AttributeValue.builder().s("user123").build()))
                    .updateExpression("SET Age = :newAge")
                    .expressionAttributeValues(expressionAttributeValues)
                    .conditionExpression(conditionsExpression)
                    .build();

            UpdateItemResponse response = client.updateItem(request);
            System.out.println("Item updated conditionally : "+ response);
        }catch (ConditionalCheckFailedException e) {
            System.out.println("Condition not met, item not updated : ");
        }catch(DynamoDbException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }finally {
            client.close();
        }
    }
}
