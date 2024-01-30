import database.Database;
import models.ngram.BasicNGramModel;
import models.ngram.NGramDataHandler;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int n = 2;

        NGramDataHandler dataHandler = new NGramDataHandler(n);

        BasicNGramModel nGramModel = new BasicNGramModel(dataHandler);

        Database database = new Database(dataHandler);

        database.addFile("JavaTest", new File("C:\\Users\\andre\\Desktop\\Andre Uni\\Tercero\\BD\\kmlkf.java"));
        database.addFile("PythonTest", new File("C:\\Users\\andre\\Desktop\\Andre Uni\\Cuarto\\PLN\\Ejercicios_PLN.py"));

        database.processFiles();

        File retrievedFile = database.getFile("JavaTest");
        if (retrievedFile != null) {
            System.out.println("Archivo recuperado: " + retrievedFile.getAbsolutePath());
        } else {
            System.out.println("Archivo no encontrado.");
        }

        List<String> suggestions = nGramModel.suggestNextTokens("public");
        System.out.println("Sugerencias: " + suggestions);
    }
}
