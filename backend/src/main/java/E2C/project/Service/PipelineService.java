package E2C.project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@PropertySource(value = {"minio.properties"} )
public class PipelineService {
    private final static Logger LOG = Logger.getGlobal();
    @Value("${minio.user}")
    private String Id;
    @Value("${minio.password}")
    private String password;

    public void ConnectPipeline() {



    }
}
