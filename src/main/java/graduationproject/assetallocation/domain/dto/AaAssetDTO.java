package graduationproject.assetallocation.domain.dto;

import graduationproject.assetallocation.domain.AaAsset;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Builder
public class AaAssetDTO {
    String assetName;

    Integer rate;

    public static AaAssetDTO from(AaAsset aaAsset){
        return AaAssetDTO.builder()
                .assetName(aaAsset.getAssetName())
                .rate(aaAsset.getRate())
                .build();
    }
}
