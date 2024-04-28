package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemResponse;

import java.util.Collections;

public class UpdateItem {
    public static void main(String[] args) {
        DynamoDbClient client =  DynamoDbClient.builder().build();
            try {
                UpdateItemRequest request = UpdateItemRequest.builder()
                        .tableName("UserTable")
                        .key(Collections.singletonMap("UserID", AttributeValue.builder()
                                        .s("user123")
                                        .build()))
                        .updateExpression("SET Age = :newAge")
                        .expressionAttributeValues(Collections.singletonMap(":newAge", AttributeValue.builder().n("90").build()))
                        .build();

                UpdateItemResponse response = client.updateItem(request);
                System.out.println("Age Updated "+ response);
            } catch (DynamoDbException e) {
                System.err.println(e.awsErrorDetails().errorMessage());;
            }finally {
                client.close();
            }
        }
    }

