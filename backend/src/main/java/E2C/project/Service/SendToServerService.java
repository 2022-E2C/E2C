package E2C.project.Service;

import com.jcraft.jsch.*;
import io.minio.DownloadObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class SendToServerService {

    private final static Logger LOG = Logger.getGlobal();

    public void MinIODataSendToServer() throws IOException, NoSuchAlgorithmException, InvalidKeyException, JSchException {
        DownloadObject();
        SendObject();
    }

    private void SendObject() throws JSchException {
        /* SCP 클래스 생성 */
        SCPUtil scpUtil = new SCPUtil();
        scpUtil.init();

        if (!scpUtil.isInVaild()) {
            System.out.println("에러");
            scpUtil = null;
        }

        /* 파일 업로드 */
        /* -------- 처리 --------- */
        File file = null;
        try{
            file= new File("./backend/executionFile/data/Test_image.jpg");
        }   catch (Exception e){
            e.printStackTrace();
        }
        String path = "/home/ubuntu/sftp";
        scpUtil.upload(path, file);
        LOG.info("Upload Complete!");


        /* 세션 종료 및 파일 닫기 */
        scpUtil.disconnection();
        scpUtil = null;
    }

    public void DownloadObject() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            /* play.min.io for test and development. */
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("https://localhost:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();
/*
                            .endpoint("https://play.min.io")
                            .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
                            .build();
*/

            try{
                // Download 'my-objectname' from 'my-bucketname' to 'my-filename'
                minioClient.downloadObject(
                        DownloadObjectArgs.builder()
                                .bucket("mdclmdclmdcl")
                                .object("Test_image.jpg")
                                .filename("./backend/executionFile/data/Test_image.jpg")   //  put directory path with the file name to be downloaded.
                                .build());
                System.out.println("mdclmdclmdcl/Test_image.jpg is successfully downloaded to ./backend/executionFile/data/Test_image.jpg");
            } catch (IllegalArgumentException e){
                LOG.info("File Already Exists!");
                LOG.info("Skipping File Download!");
            }

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    public class SCPUtil {
        private Session session;
        private Channel channel;
        private ChannelSftp channelSftp;
        private boolean inVaild = false;

        //여기에 .pem 파일의 절대경로를 지정한다.
        private String keyname = "./backend/src/main/resources/key/mdcl-key.pem";
        //여기에 EC2 instance 도메인 주소를 적는다.
        private String publicDNS = "ec2-3-145-68-102.us-east-2.compute.amazonaws.com";
        public void init() throws JSchException {
            /* JSch 라이브러리 호출 */
            JSch jsch = new JSch();
            try {
                String privateKey = keyname;
                jsch.addIdentity(privateKey);

                /* 세션 호출 */
                session = jsch.getSession("ubuntu", publicDNS, 22);
//                session = jsch.getSession(serverId, serverIp, 22);
//                session.setPassword(serverPw);

                /* 세션 키 없이 호출 */
                session.setConfig("PreferredAuthentications", "publickey");
                session.setConfig("StrictHostKeyChecking","no");
                session.setConfig("GSSAPIAuthentication","no");
                session.setServerAliveInterval(120 * 1000);
                session.setServerAliveCountMax(1000);
                session.setConfig("TCPKeepAlive","yes");

                /* 세션 연결 */
                session.connect();
                LOG.info("Session Connected!");

                /* 채널 방식을 설정한 후 채널을 이용하여 Upload, Download */
                channel = session.openChannel("sftp");
                channel.connect();

                channelSftp = (ChannelSftp) channel;
                inVaild = true;
            } catch (JSchException e) {
                e.printStackTrace();
            }
        }

        /**
         * @param path : ls 명령어를 입력하려고 하는 path 저장소
         * @return
         */
        public Vector<ChannelSftp.LsEntry> getFileList(String path) {
            Vector<ChannelSftp.LsEntry> list = null;
            try {
                channelSftp.cd(path);
                list = channelSftp.ls(".");

            } catch (Exception e) {
                // TODO: handle exception
            }
            return list;
        }

        /**
         * @param path : serverVO.path 를 통해 scp로 들어간 서버로 접속하여 upload한다.
         * @param file : File file을 객체를 받음
         */
        public void upload(String path, File file) {
            FileInputStream in = null;
            try {
                in = new FileInputStream(file);
                channelSftp.cd(path);
                channelSftp.put(in, file.getName());
            } catch (SftpException | FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(in != null)
                        in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        /**
         * @param path : serverVO.path 를 통해 scp로 들어간 서버로 접속하여 download한다.
         * @param fileName : 특정 파일의 이름을 찾아서 다운로드 한다.
         * @param userPath
         */
        public void download(String path, String fileName, String userPath) {
            InputStream in = null;
            FileOutputStream out = null;
            try {
                channelSftp.cd(path);
                in = channelSftp.get(fileName);
            } catch (SftpException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                String fullpath = userPath + File.separator + fileName;
                out = new FileOutputStream(new File(fullpath));
                int i;

                while ((i = in.read()) != -1) {
                    out.write(i);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        /**
         * 서버와의 연결을 끊는다.
         */
        public void disconnection() {
            channelSftp.quit();
            channel.disconnect();
            session.disconnect();
            setInVaild(false);
        }

        public boolean isInVaild() {
            return inVaild;
        }

        public void setInVaild(boolean inVaild) {
            this.inVaild = inVaild;
        }

    }

}

