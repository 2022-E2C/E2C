package E2C.project.Controller;

import E2C.project.Service.MonitorService;
import E2C.project.domain.BucketDto;
import E2C.project.domain.ObjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MoniterController {

    private final MonitorService monitorService;

    @GetMapping("bucket-detail-page")
    public ResponseEntity<List<BucketDto>> giveBucketInformation(){
        return ResponseEntity.ok(monitorService.MinIOGetBucketList());
    }

    @GetMapping("object-detail-page")
    public ResponseEntity<List<ObjectDto>> giveObjectInformation(@RequestParam String bucketName){
        return ResponseEntity.ok(monitorService.MinIOGetObjectList(bucketName));
    }

}
