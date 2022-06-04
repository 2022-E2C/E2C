package E2C.project.Service;

import io.minio.DownloadObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@PropertySource(value = {"minio.properties"})
public class DataProcessingService {

    private final static Logger LOG = Logger.getGlobal();

    @Value("${minio.user}")
    private String Id;
    @Value("${minio.password}")
    private String password;


    public void MinIOImageProcess() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        LOG.info("1");
        DownloadObject();
        LOG.info("2");
        ProcessImage();
        LOG.info("3");
    }

    private void ProcessImage() {
        try {
            LOG.info("Processing Image...");
            Runtime.getRuntime().exec(
                    "gnome-terminal -- chmod +x /home/mdcl/IdeaProjects/E2C/backend/executionFile/processStart.sh");
            Thread.sleep(500);
            Process p = Runtime.getRuntime().exec("gnome-terminal -- /home/mdcl/IdeaProjects/E2C/backend/executionFile/processStart.sh");
            p.waitFor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void DownloadObject() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            /* play.min.io for test and development. */
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://localhost:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();

            try {
                // Download 'my-objectname' from 'my-bucketname' to 'my-filename'
                minioClient.downloadObject(
                        DownloadObjectArgs.builder()
                                .bucket("images1")
                                .object("Test_image.jpg")
//                                .filename("./backend/executionFile/data/Test_image.jpg")   //  put directory path with the file name to be downloaded.
                                .filename("/home/mdcl/IdeaProjects/E2C/backend/executionFile/data/Test_image.jpg")
                                .build());
                System.out.println("images1/Test_image.jpg is successfully downloaded to ./backend/executionFile/data/Test_image.jpg");
            } catch (IllegalArgumentException e) {
                LOG.info("File Already Exists!");
                LOG.info("Skipping File Download!");
            }

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
