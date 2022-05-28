package E2C.project.Service;

import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
@PropertySource(value = {"minio.properties"} )
public class BootService {
    private final static Logger LOG = Logger.getGlobal();

    @Value("${minio.user}")
    private String Id;
    @Value("${minio.password}")
    private String password;

    public void MinIOModuleBoot(){
        File file = null;
        final String projectDataUrl;
        if (OsUtils.isWindows()) {
            LOG.info("Operating System : Windows");
            projectDataUrl = ".\\executionFile\\data";

            try {
                file = new File(projectDataUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!file.exists()) {
                LOG.info("file doesn't exist");
                file.mkdir();
            }
            try {
                Runtime.getRuntime().exec("cmd /c start .\\executionFile\\minioStart.bat");
                // Create a minioClient with the MinIO server playground, its access key and secret key.

            } catch (IOException e){
                e.printStackTrace();
            }
        }

        else {  //  OS: Linux or else
            if (OsUtils.isLinux()) {
                LOG.info("Operating System : Linux");
            } else {
                LOG.info("Unexpected Operating System.\n Trying as Linux");
            }
            try {
                LOG.info("Running MinIO...");
                Runtime.getRuntime().exec("gnome-terminal -- /home/mdcl/IdeaProjects/E2C/backend/executionFile/minioStart.sh");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

    public void MinIOModuleTerminate() {
        LOG.info("Terminating MinIO...");
        if(OsUtils.isWindows()){
            try {
                Runtime.getRuntime().exec("cmd /c start .\\executionFile\\minioStop.bat");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{   //  OS: Linux or else
            try {
                Runtime.getRuntime().exec("gnome-terminal -- /home/mdcl/IdeaProjects/E2C/backend/executionFile/minioStop.sh");
//                Runtime.getRuntime().exec("sh -c ./executionFile/minioStart.sh");
//                Runtime.getRuntime().exec("gnome-terminal -x ./executionFile/minioStop.sh");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static final class OsUtils {
        private static String OS = null;

        public static String getOsName() {
            if (OS == null) {
                OS = System.getProperty("os.name");
            }
            return OS;
        }

        public static boolean isWindows() {
            return getOsName().startsWith("Windows");
        }

        public static boolean isLinux() {
            return getOsName().startsWith("Linux");
        }
    }
}


