package E2C.project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BucketInfoDTO {
    private List<BucketDto> bucketList;
    private double minioUsage;
}
