package E2C.project.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
