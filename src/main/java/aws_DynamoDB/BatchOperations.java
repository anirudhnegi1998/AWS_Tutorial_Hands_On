package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BatchOperations {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();

        try{
            List<WriteRequest> writeRequests = new ArrayList<>();

            Map<String, AttributeValue> item1 = Map.of(
                    "UserID", AttributeValue.builder().s("user234").build(),
                    "FirstName", AttributeValue.builder().s("Umang").build(),
                    "LastName", AttributeValue.builder().s("Goyal").build(),
                    "Age", AttributeValue.builder().n("55").build()
            );

            writeRequests.add(WriteRequest.builder().putRequest(PutRequest.builder().item(item1).build()).build());

            Map<String, AttributeValue> item2 = Map.of(
                    "UserID", AttributeValue.builder().s("user123").build(),
                    "FirstName", AttributeValue.builder().s("Ashutosh").build(),
                    "LastName", AttributeValue.builder().s("Chauhan").build(),
                    "Age", AttributeValue.builder().n("51").build()
            );

            writeRequests.add(WriteRequest.builder().putRequest(PutRequest.builder().item(item2).build()).build());

            BatchWriteItemRequest request = BatchWriteItemRequest.builder()
                    .requestItems(Map.of("UserTable",writeRequests))
                    .build();

            BatchWriteItemResponse response = client.batchWriteItem(request);
            System.out.println("Batch Insert Successfully: "+ response);

        }catch (DynamoDbException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }finally {
            client.close();
        }
    }
}
