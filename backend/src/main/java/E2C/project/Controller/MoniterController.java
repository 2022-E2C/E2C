package E2C.project.Controller;

import E2C.project.Service.BootService;
import E2C.project.Service.MonitorService;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MoniterController {

    private final MonitorService monitorService;

    @PostMapping("bucket-detail-page")
    public ResponseEntity<List<Bucket>> giveBucketInformation(){
        return ResponseEntity.ok(monitorService.MinIOGetBucketList());
    }

    @PostMapping("object-detail-page")
    public ResponseEntity<Result<Item>> giveObjectInformation(){
        return ResponseEntity.ok(monitorService.MinIOGetObjectList());
    }

}
