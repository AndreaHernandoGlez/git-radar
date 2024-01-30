package code.metrics;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CodeMetricsLambdaHandler implements RequestHandler<S3Event, Void> {

    private final AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
    private final AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard().build();

    @Override
    public Void handleRequest(S3Event event, Context context) {
        event.getRecords().forEach(record -> {
            String bucketName = record.getS3().getBucket().getName();
            String key = record.getS3().getObject().getKey();
            S3Object s3Object = s3Client.getObject(bucketName, key);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(s3Object.getObjectContent()))) {
                FileAnalyzer fileAnalyzer = new FileAnalyzer();
                Metrics metrics = fileAnalyzer.analyze(reader);
                saveMetricsToDynamoDB(metrics, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return null;
    }

    private void saveMetricsToDynamoDB(Metrics metrics, String key) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("FileKey", new AttributeValue(key));

        PutItemRequest request = new PutItemRequest()
                .withTableName("CodeMetrics")
                .withItem(item);
        dynamoDBClient.putItem(request);
    }
}
