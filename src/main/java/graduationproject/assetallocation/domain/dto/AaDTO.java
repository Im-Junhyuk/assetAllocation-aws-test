package graduationproject.assetallocation.domain.dto;

import graduationproject.assetallocation.domain.RebalancingPeriod;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AaDTO {
    Long id;
    String name;
    List<AaAssetDTO> aaAssets;
    String startDay;
    String endDay;
    Long initialCash;
    RebalancingPeriod rebalancingPeriod;
    LocalDate createdDay;
    LocalDate lastModifiedDay;
}
