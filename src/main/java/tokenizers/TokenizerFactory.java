package tokenizers;

public class TokenizerFactory {
    public Tokenizer getTokenizer(String language) {
        if ("java".equalsIgnoreCase(language)) {
            return new JavaTokenizer();
        } else if ("python".equalsIgnoreCase(language)) {
            return new PythonTokenizer();
        } else {
            return new NullTokenizer();
        }
    }
}
