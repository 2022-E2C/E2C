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

    public void MinIOModuleBoot() {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else {  //  OS: Linux or else
            if (OsUtils.isLinux()) {
                LOG.info("Operating System : Linux");
            }
            else {
                LOG.info("Unexpected Operating System.\n Trying as Linux");
            }
            /*projectDataUrl = "./executionFile/data";
            try {
                file = new File(projectDataUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!file.exists()) {
                LOG.info("file doesn't exist");
                file.mkdir();
            }*/
            try {
                LOG.info("Running MinIO...");
                Runtime.getRuntime().exec("gnome-terminal -- /home/mdcl/IdeaProjects/E2C/executionFile/minioStart.sh");
//                Runtime.getRuntime().exec("chmod +x minioStart.sh && gnome-terminal -- ./executionFile/minioStart.sh");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void MinIOModuleTerminate() {
        if(OsUtils.isWindows()){
            try {
                Runtime.getRuntime().exec("cmd /c start .\\executionFile\\minioStop.bat");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{   //  OS: Linux or else
            try {
                Runtime.getRuntime().exec("gnome-terminal -- /home/mdcl/IdeaProjects/E2C/executionFile/minioStop.sh");
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


