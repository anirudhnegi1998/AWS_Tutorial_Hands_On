package aws_DynamoDB;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import javax.swing.*;

public class ListTable {
    public static void main(String[] args) {
        DynamoDbClient client = DynamoDbClient.builder().build();
        try{
            ListTablesRequest request = ListTablesRequest.builder().build();
            ListTablesResponse response = client.listTables(request);
            System.out.println("List of DyamoDB tables");
            response.tableNames().forEach(System.out::println);
        }catch(DynamoDbException e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }finally {
            client.close();
        }
    }
}
