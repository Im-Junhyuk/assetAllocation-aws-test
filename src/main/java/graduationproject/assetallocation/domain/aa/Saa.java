package graduationproject.assetallocation.domain.aa;

import graduationproject.assetallocation.domain.AaAsset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.dto.AaDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("S")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Saa extends Aa {

    // 생성 메소드
    public static Aa createSaa(String name, Member member, List<AaAsset> aaAssets,
                               String startDay, String endDay, Long initialCash,
                               RebalancingPeriod rebalancingPeriod){
        Saa saa = new Saa();
        saa.setName(name);
        saa.setMember(member);
        for (AaAsset aaAsset:aaAssets){
            saa.addAaAsset(aaAsset);
        }

        saa.setStartDay(startDay);
        saa.setEndDay(endDay);
        saa.setInitialCash(initialCash);
        saa.setRebalancingPeriod(rebalancingPeriod);
        saa.setCreatedDay();

        return saa;
    }
    @Override
    public AaDTO toDTO() {
        return null;
    }

    @Override
    public String type() {
        return "static";
    }



    @Override
    public Aa updateFromDTO(AaDTO saaDTO, List<AaAsset> aaAssets) {

        this.setName(saaDTO.getName());
        this.setStartDay(saaDTO.getStartDay());
        this.setEndDay(saaDTO.getEndDay());
        this.setInitialCash(saaDTO.getInitialCash());
        this.setRebalancingPeriod(saaDTO.getRebalancingPeriod());
        this.setLastModifiedDay();

        this.getAaAssets().clear();
        this.getAaAssets().addAll(aaAssets);
//        for(AaAsset aaAsset: aaAssets){
//            this.addAaAsset(aaAsset);
//        }
        //this.setAaAssets(aaAssets);
        this.addAaAssetList(aaAssets);
        return this;

    }
}
