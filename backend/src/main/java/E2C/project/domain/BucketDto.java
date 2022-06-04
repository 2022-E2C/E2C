package E2C.project.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.minio.messages.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BucketDto {
    private String name;
    private ZonedDateTime createdTime;
    private int objectNumber;
    private List<ObjectDto> objects;
}
