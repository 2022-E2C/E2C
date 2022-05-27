package E2C.project.Controller;

import E2C.project.Service.BootService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("boot")
@RequiredArgsConstructor
public class BootController {

    private final BootService bootService;

    @GetMapping("/")
    public void booting(){
        bootService.MinIOModuleBoot();
    }
}
