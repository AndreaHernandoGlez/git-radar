import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.io.File;
import java.net.URI;
import java.nio.file.Path;

public class S3Uploader {

    private final S3Client s3Client;

    public S3Uploader(String endpoint) {
        this.s3Client = S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.of("us-east-1"))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("test", "test")))
                .build();
    }

    public void uploadFileToBucket(String bucketName, String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            System.out.println("El archivo proporcionado no existe o es un directorio.");
            return;
        }

        try {
            String key = file.getName();
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            s3Client.putObject(putObjectRequest, Path.of(file.getAbsolutePath()));
            System.out.println("Archivo subido: " + file.getName());
        } catch (Exception e) {
            System.err.println("Error al subir el archivo: " + file.getName());
            e.printStackTrace();
        }
    }
}