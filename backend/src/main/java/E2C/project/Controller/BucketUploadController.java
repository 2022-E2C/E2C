package E2C.project.Controller;

import E2C.project.Service.BucketUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BucketUploadController {

    private final BucketUploadService bucketUploadService;

    @GetMapping("/makebk")
    public void makeBucket(){
        bucketUploadService.MinIOMakeBucket();
    }

    @GetMapping("/bkupload")
    public void bucketUpload(){
        bucketUploadService.MinIOBucketUpload();
    }

}
