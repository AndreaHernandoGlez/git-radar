package code.metrics;

public class CodeMetrics {
    private int linesOfCode;
    private int cyclomaticComplexity;
    private double duplicationPercentage;

    public CodeMetrics(int linesOfCode, int cyclomaticComplexity, double duplicationPercentage) {
        this.linesOfCode = linesOfCode;
        this.cyclomaticComplexity = cyclomaticComplexity;
        this.duplicationPercentage = duplicationPercentage;
    }
}

