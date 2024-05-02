package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.TimeToLiveSpecification;
import software.amazon.awssdk.services.dynamodb.model.UpdateTimeToLiveRequest;

public class TimeToLiveRequest {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();

        try{
            UpdateTimeToLiveRequest request = UpdateTimeToLiveRequest.builder()
                    .tableName("UserTable")
                    .timeToLiveSpecification(TimeToLiveSpecification.builder()
                            .attributeName("September 20, 2024")
                            .enabled(true)
                            .build())
                    .build();
            client.updateTimeToLive(request);
            System.out.println("TTL configured"); // Time to Live configured
        }catch (DynamoDbException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }finally {
            client.close();
        }
    }
}
