package E2C.project.Controller;

import E2C.project.Service.BucketUploadService;
import E2C.project.Service.DataProcessingService;
import E2C.project.Service.PipelineService;
import E2C.project.Service.SendToServerService;
import com.jcraft.jsch.JSchException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class PipelineController {

    private final PipelineService pipelineService;
    private final BucketUploadService bucketUploadService;
    private final SendToServerService sendToServerService;
    private final DataProcessingService dataProcessingService;

    @GetMapping("/pipeline")
    public void pipeline() throws JSchException, IOException, NoSuchAlgorithmException, InvalidKeyException, InterruptedException {
        // Bucket Upload Service
        bucketUploadService.MinIOBucketUpload();
        Thread.sleep(300);

        //  Data Process Service
//        dataProcessingService.MinIOImageProcess();
//        Thread.sleep(300);

        //  Send To Server Service
//        sendToServerService.MinIODataSendToServer();
    }


}
