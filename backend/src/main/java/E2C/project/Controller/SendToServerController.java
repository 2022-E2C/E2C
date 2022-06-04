package E2C.project.Controller;

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
public class SendToServerController {

    private final SendToServerService sendToServerService;

    @GetMapping("/send-to-server")
    public void sendToServer() throws IOException, NoSuchAlgorithmException, InvalidKeyException, JSchException {
        sendToServerService.MinIODataSendToServer();
    }


}
