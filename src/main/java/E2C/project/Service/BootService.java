package E2C.project.Service;

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
        final String projectDataUrl = ".\\executionFile\\data";
        file = new File(projectDataUrl);
        if (!file.exists()){
            LOG.info("file doesn't exist");
            file.mkdir();
        }
        try {
            Runtime.getRuntime().exec("cmd /c start .\\executionFile\\minioStart.bat");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void MinIOModuleTerminate(){
        try {
            Runtime.getRuntime().exec("cmd /c start .\\executionFile\\minioStop.bat");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
