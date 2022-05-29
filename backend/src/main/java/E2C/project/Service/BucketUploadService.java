package E2C.project.Service;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class BucketUploadService {

    private final static Logger LOG = Logger.getGlobal();
    @Value("${minio.user}")
    private String Id;
    @Value("${minio.password}")
    private String password;
    public void MinIOMakeBucket() {

        try {
            Thread.sleep(3000);
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://localhost:9000")
                            .credentials(Id, password)
                            .build();
            // Make 'temperature1' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("temperature1").build());
            if (!found) {
                // Make a new bucket called 'temperature1'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("temperature1").build());
            } else {
                System.out.println("Bucket 'temperature1' already exists.");
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (NoSuchAlgorithmException | InvalidKeyException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void MinIOBucketUpload(){

    }
}
