package tokenizers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import database.DynamoDBClient;

import java.util.List;

public class TokenizerLambdaHandler implements RequestHandler<LambdaRequest, LambdaResponse> {
    private final DynamoDBClient dynamoDBClient = new DynamoDBClient();

    @Override
    public LambdaResponse handleRequest(LambdaRequest request, Context context) {
        TokenizerFactory tokenizerFactory = new TokenizerFactory();
        Tokenizer tokenizer = tokenizerFactory.getTokenizer(request.getLanguage());
        List<String> tokens = tokenizer.tokenize(request.getSourceCode());

        dynamoDBClient.saveTokensToDynamoDB(tokens);

        return new LambdaResponse(tokens);
    }
}

