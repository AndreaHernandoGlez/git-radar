public class Main {
    public static void main(String[] args) {
        S3Uploader uploader = new S3Uploader("http://localhost:4566");
        uploader.uploadFileToBucket("git-radar-codefiles", "C:/Users/cynth/Documents/Cuarto año GCID/TSCD/Ficheros/Ficheros/Agenda.java"); // Asegúrate de cambiar esto a la ruta de un archivo real
    }
}
