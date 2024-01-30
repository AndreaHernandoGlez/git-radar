/*package code.metrics;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CodeMetricsCalculator {
    public static CodeMetrics calculateMetrics(InputStream sourceCodeStream) {
        try {
            JavaParser javaParser = new JavaParser();

            List<String> sourceCodeLines = readSourceCode(sourceCodeStream);

            String sourceCode = String.join("\n", sourceCodeLines);

            ParseResult<CompilationUnit> result = javaParser.parse(sourceCode);

            if (result.isSuccessful()) {
                CompilationUnit cu = result.getResult().get();

                List<String> codeLines = sourceCodeLines;
                int linesOfCode = codeLines.size();

                CyclomaticComplexityVisitor complexityVisitor = new CyclomaticComplexityVisitor();
                complexityVisitor.visit(cu, null);
                int cyclomaticComplexity = complexityVisitor.getComplexity();

                return new CodeMetrics(linesOfCode, cyclomaticComplexity, 0.0);
            } else {
                throw new RuntimeException("Error al analizar el código fuente: " + result.getProblems());
            }
        } catch (ParseProblemException e) {
            throw new RuntimeException("Error al analizar el código fuente: " + e.getMessage(), e);
        }
    }

    public class CyclomaticComplexityVisitor extends VoidVisitorAdapter<Void> {
        private int complexity = 1;

        @Override
        public void visit(MethodDeclaration n, Void arg) {
            super.visit(n, arg);
            complexity += calculateCyclomaticComplexity(n);
        }

        private int calculateCyclomaticComplexity(Node node) {
            if (node instanceof IfStmt) {
                return 1 + ((IfStmt) node).getElseStmt().map(this::calculateCyclomaticComplexity).orElse(0);
            } else if (node instanceof ForStmt || node instanceof ForEachStmt || node instanceof WhileStmt) {
                return 1;
            } else if (node instanceof SwitchStmt) {

                return ((SwitchStmt) node).getEntries().size();
            } else {
                return 0;
            }
        }
        public int getComplexity() {
            return complexity;
        }
    }
    private static List<String> readSourceCode(InputStream sourceCodeStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(sourceCodeStream));
        return reader.lines().collect(Collectors.toList());
    }
}

*/