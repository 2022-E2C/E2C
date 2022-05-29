package E2C.project.Service;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@PropertySource(value = {"minio.properties"} )
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
            // Make 'images1' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("images1").build());
            if (!found) {
                // Make a new bucket called 'images1'.
                LOG.info("Attempting to make bucket : \"images1\"");
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("images1").build());
            } else {
                System.out.println("Bucket 'images1' already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (NoSuchAlgorithmException | InvalidKeyException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void MinIOBucketUpload() {
        try {
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://localhost:9000")
                            .credentials(Id, password)
                            .build();

            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("images1").build());
            if (!found) {
                // Make a new bucket called 'images1'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("images1").build());
            } else {
                System.out.println("Bucket 'images1' already exists.\n Not making a new bucket.");
            }

            // Upload './backend/testDataset/Test_image.jpg' as object name 'Test_image.jpg' to bucket
            // 'images1'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("images1")
                            .object("Test_image.jpg")
                            .filename("testDataset/Test_image.jpg")
//                            .filename("/home/mdcl/IdeaProjects/E2C/backend/testDataset/Test_image.jpg")   //  this works !
                            .contentType("image/jpg")
                            .build());

            System.out.println(
                    "'./backend/testDataset/Test_image.jpg' is successfully uploaded as "
                            + "object 'Test_image.jpg' to bucket 'images1'.");

        } catch (MinioException e){
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
