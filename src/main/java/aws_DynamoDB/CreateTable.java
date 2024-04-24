package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class CreateTable {
    public static void main(String[] args) {
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder().build();
        try{
            AttributeDefinition partitionKey = AttributeDefinition.builder()
                    .attributeName("UserID")
                    .attributeType(ScalarAttributeType.S)
                    .build();

            KeySchemaElement keySchemaElement = KeySchemaElement.builder()
                    .attributeName("UserID")
                    .keyType(KeyType.HASH)
                    .build();

            ProvisionedThroughput provisionedThroughput = ProvisionedThroughput.builder()
                    .readCapacityUnits(5L)
                    .writeCapacityUnits(5L)
                    .build();

            CreateTableRequest createTableRequest = CreateTableRequest.builder()
                    .tableName("UserTable")
                    .attributeDefinitions(partitionKey)
                    .keySchema(keySchemaElement)
                    .provisionedThroughput(provisionedThroughput)
                    .build();

            CreateTableResponse createTableResponse = dynamoDbClient.createTable(createTableRequest);
            System.out.println("Created Table: "+ createTableResponse.tableDescription().tableName());
            dynamoDbClient.close();
        }catch(DynamoDbException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        finally {
            dynamoDbClient.close();
        }
    }
}
