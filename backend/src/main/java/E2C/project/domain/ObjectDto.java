package E2C.project.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Builder
public class ObjectDto {
    private String name;
    private long size;
    private ZonedDateTime lastModified;
}
