package E2C.project.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

public class MonitorService {

    private final static Logger LOG = Logger.getGlobal();
    @Value("${minio.user}")
    private String Id;
    @Value("${minio.password}")
    private String password;

    public List<Bucket> MinIOGetBucketList() {

        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://localhost:9000")
                        .credentials(Id, password)
                        .build();

        List<Bucket> bucketList;
        try{
            bucketList = minioClient.listBuckets();
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            throw new RuntimeException(e);
        }
        for (Bucket bucket : bucketList) {
            System.out.println(bucket.creationDate() + ", " + bucket.name());
        }

        return bucketList;
    }

    public Result<Item> MinIOGetObjectList() {

        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://localhost:9000")
                        .credentials(Id, password)
                        .build();

        // Lists objects information.
        Iterable<Result<Item>> results =
                minioClient.listObjects(ListObjectsArgs.builder().bucket("images1").build());
        try {
            for (Result<Item> result : results) {
                Item item = result.get();
                System.out.println(item.lastModified() + "\t" + item.size() + "\t" + item.objectName());
            }
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            throw new RuntimeException(e);
        }

        return (Result<Item>) results;
    }
}
