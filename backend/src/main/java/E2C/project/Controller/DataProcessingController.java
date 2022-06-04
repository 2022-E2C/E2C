package E2C.project.Controller;

import E2C.project.Service.DataProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class DataProcessingController {

    private final DataProcessingService dataProcessingService;

    @GetMapping("/processImage")
    public void process() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        dataProcessingService.MinIOImageProcess();
    }

}
