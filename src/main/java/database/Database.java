package database;

import models.ngram.TokenProcessor;
import tokenizers.Tokenizer;
import tokenizers.TokenizerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private final Map<String, File> files;
    private final TokenProcessor tokenProcessor;
    private final TokenizerFactory tokenizerFactory;

    public Database(TokenProcessor tokenProcessor) {
        this.files = new HashMap<>();
        this.tokenProcessor = tokenProcessor;
        this.tokenizerFactory = new TokenizerFactory();
    }

    public void addFile(String identifier, File file) {
        files.put(identifier, file);
    }

    public File getFile(String identifier) {
        return files.get(identifier);
    }

    public void processFiles() {
        files.values().forEach(this::processFile);
    }

    private void processFile(File file) {
        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            Tokenizer tokenizer = tokenizerFactory.getTokenizer(file);
            List<String> tokens = tokenizer.tokenize(content);
            tokenProcessor.processTokens(tokens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}