package database;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamoDBClient {
    private final AmazonDynamoDB client;

    public DynamoDBClient() {
        this.client = AmazonDynamoDBClientBuilder.standard().build();
    }

    public void saveTokensToDynamoDB(List<String> tokens) {
        for (String token : tokens) {
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("token", new AttributeValue(token));

            PutItemRequest putItemRequest = new PutItemRequest()
                    .withTableName("TokenDB")
                    .withItem(item);

            client.putItem(putItemRequest);
        }
    }
}
