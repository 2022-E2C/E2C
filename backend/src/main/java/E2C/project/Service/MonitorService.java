package E2C.project.Service;

import E2C.project.dto.BucketDto;
import E2C.project.dto.BucketInfoDTO;
import E2C.project.dto.ObjectDto;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@PropertySource(value = {"minio.properties"} )
public class MonitorService {

    private final static Logger LOG = Logger.getGlobal();
    @Value("${minio.user}")
    private String Id;
    @Value("${minio.password}")
    private String password;

    public BucketInfoDTO MinIOGetBucketList() {
        double minioUsage = 0;

        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://localhost:9000")
                        .credentials(Id, password)
                        .build();

        List<Bucket> bucketList;
        List<BucketDto> bucketInfoList;
        BucketInfoDTO minioInfo;

        try{
            // Get minio bucket list
            bucketList = minioClient.listBuckets();
            bucketInfoList = new ArrayList<>();

            // Generate bucket list
            for (Bucket bucket : bucketList) {
                List<ObjectDto> objectList = new ArrayList<>();
                String name = bucket.name();

                // Get Minio objects of bucket
                Iterable<Result<Item>> objects = minioClient.listObjects
                        (ListObjectsArgs.builder().bucket(name).build());

                // Generate object list
                for (Result<Item> result : objects) {
                    Item item = result.get();
                    minioUsage += item.size();
                    objectList.add(ObjectDto.builder()
                            .name(item.objectName())
                            .lastModified(item.lastModified())
                            .size(item.size())
                            .build());
                    System.out.println(item.lastModified() + "\t" + item.size() + "\t" + item.objectName());
                }

                // Generate bucket & object Info
                bucketInfoList.add(BucketDto.builder()
                        .name(name)
                        .createdTime(bucket.creationDate())
                        .objects(objectList)
                        .objectNumber(objectList.size())
                        .build());

                // bucket total size
                System.out.println(bucket.creationDate() + ", " + bucket.name());   // bucket log
            }
            // KB 단위 변환,
            minioUsage = (Math.round(minioUsage/100))/10.0;
            minioInfo = BucketInfoDTO.builder().
//                    minioUsage(Math.round(minioUsage)/(long) 10.0).
                    minioUsage(minioUsage).
                    bucketList(bucketInfoList)
                    .build();

        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            throw new RuntimeException(e);
        }
        return minioInfo;
    }

    public List<ObjectDto> MinIOGetObjectList(String BucketName) {

        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://localhost:9000")
                        .credentials(Id, password)
                        .build();

        // Lists objects information.
        List<ObjectDto> objectList = new ArrayList<>();

        try {
            Iterable<Result<Item>> results =
                minioClient.listObjects(ListObjectsArgs.builder().bucket(BucketName).build());
            for (Result<Item> result : results) {
                Item item = result.get();
                objectList.add(ObjectDto.builder()
                        .name(item.objectName())
                        .lastModified(item.lastModified())
                        .size(item.size())
                        .build());

                System.out.println(item.lastModified() + "\t" + item.size() + "\t" + item.objectName());
            }
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            throw new RuntimeException(e);
        }

        return objectList;
    }
}
