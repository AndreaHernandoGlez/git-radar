package models.ngram;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NGramDataHandler implements TokenProcessor {
    private final int n;
    private final Map<String, Map<String, Integer>> nGrams;

    public NGramDataHandler(int n) {
        this.n = n;
        this.nGrams = new HashMap<>();
    }

    @Override
    public void processTokens(List<String> tokens) {
        for (int i = 0; i <= tokens.size() - n; i++) {
            String key = String.join(" ", tokens.subList(i, i + n - 1));
            String nextToken = tokens.get(i + n - 1);
            nGrams.putIfAbsent(key, new HashMap<>());
            Map<String, Integer> tokenMap = nGrams.get(key);
            tokenMap.put(nextToken, tokenMap.getOrDefault(nextToken, 0) + 1);
        }
    }

    public Map<String, Map<String, Integer>> getNGrams() {
        return nGrams;
    }
}