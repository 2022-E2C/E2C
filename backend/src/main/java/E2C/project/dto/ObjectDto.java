package E2C.project.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
public class ObjectDto {
    private String name;
    private long size;
    private ZonedDateTime lastModified;
}
