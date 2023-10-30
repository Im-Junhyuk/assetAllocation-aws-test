package graduationproject.assetallocation.domain.dto;

import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.aa.Aa;
import graduationproject.assetallocation.domain.aa.Saa;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class SaaDTO extends AaDTO{


    public static AaDTO from(Aa aa){
        if (aa == null){
            return null;
        }

        return SaaDTO.builder()
                .id(aa.getId())
                .name(aa.getName())
                .aaAssets(aa.getAaAssets().stream()
                        .map((s)-> AaAssetDTO.from(s))
                        .collect(Collectors.toList()))
                .startDay(aa.getStartDay())
                .endDay(aa.getEndDay())
                .initialCash(aa.getInitialCash())
                .rebalancingPeriod(aa.getRebalancingPeriod())
                .createdDay(aa.getCreatedDay())
                .lastModifiedDay(aa.getLastModifiedDay())
                .build();
    }

}

