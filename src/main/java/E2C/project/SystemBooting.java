//import io.minio.BucketExistsArgs;
//import io.minio.MakeBucketArgs;
//import io.minio.MinioClient;
//import io.minio.UploadObjectArgs;
//import io.minio.errors.MinioException;
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//public class SystemBooting {
//    public static void main(String[] args)
//            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
//        try {
//            // Create a minioClient with the MinIO server playground, its access key and secret key.
//            MinioClient minioClient =
//                    MinioClient.builder()
//                            .endpoint("https://play.min.io")
//                            .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
//                            .build();
//
//            // Make 'temperature1' bucket if not exist.
//            boolean found =
//                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("temperature1").build());
//            if (!found) {
//                // Make a new bucket called 'temperature1'.
//                minioClient.makeBucket(MakeBucketArgs.builder().bucket("temperature1").build());
//            } else {
//                System.out.println("Bucket 'temperature1' already exists.");
//            }
//
//            // Upload './data-t1-2022.txt' as object name 'data-t1.txt' to bucket
//            // 'temperature1'.
//            minioClient.uploadObject(
//                    UploadObjectArgs.builder()
//                            .bucket("temperature1")
//                            .object("data-t1.txt")
//                            .filename("src/data-t1-2022.txt")
//                            .build());
//            System.out.println(
//                    "'src/data-t1-2022.txt' is successfully uploaded as "
//                            + "object 'data-t1.txt' to bucket 'temperature1'.");
//        } catch (MinioException e) {
//            System.out.println("Error occurred: " + e);
//            System.out.println("HTTP trace: " + e.httpTrace());
//        }
//    }
//}