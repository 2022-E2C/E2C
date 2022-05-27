package E2C.project.Service;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class BootService {
    private final static Logger LOG = Logger.getGlobal();
    public void MinIOModuleBoot(){
        File file;
        final String projectDataUrl = "D:\\oh\\E2C\\data";
        file = new File(projectDataUrl);
        if (!file.exists()){
            LOG.info("file doesn't exist");
            file.mkdir();
        }
        try {
            Runtime.getRuntime().exec("./executionFile/minioStart.bat");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void MinIOModuleTerminate(){
        try {
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("https://localhost:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();
            Runtime.getRuntime().exec("./executionFile/minioStop.bat");
//        }catch (MinioException e){
//            System.out.println("Error occurred: " + e);
//            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
