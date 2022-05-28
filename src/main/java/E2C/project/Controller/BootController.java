package E2C.project.Controller;

import E2C.project.Service.BootService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BootController {

    private final BootService bootService;

    @GetMapping("/boot")
    public void booting(){
        bootService.MinIOModuleBoot();
    }

    @GetMapping("/terminate")
    public void terminate(){
        bootService.MinIOModuleTerminate();
    }
}
