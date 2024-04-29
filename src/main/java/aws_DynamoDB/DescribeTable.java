package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.TableDescription;

public class DescribeTable {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();

        try{
            String tableName = "UserTable";
            DescribeTableRequest request = DescribeTableRequest.builder()
                    .tableName(tableName)
                    .build();
            DescribeTableResponse response = client.describeTable(request);
            TableDescription tableDescription = response.table();
            System.out.println("Table described: " + tableDescription.tableName());
            System.out.println("Table status: " + tableDescription.tableStatus());
            System.out.println("Table item count: " + tableDescription.itemCount());
            System.out.println("Table provision throughput: " + tableDescription.provisionedThroughput());
        }catch (DynamoDbException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }finally {
            client.close();
        }
    }
}
