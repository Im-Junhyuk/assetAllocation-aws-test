package graduationproject.assetallocation.domain.aa;

import graduationproject.assetallocation.domain.AaAsset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.dto.AaDTO;
import graduationproject.assetallocation.domain.dto.DaaDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@DiscriminatorValue("D")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Daa extends Aa {

    private String strategyType; // abs, rel

    public static Daa createDaa(AaDTO aaDTO, List<AaAsset> aaAssets, Member member) {
        DaaDTO daaDTO = (DaaDTO) aaDTO;
        return (Daa) Daa.builder()
                .id(daaDTO.getId())
                .name(daaDTO.getName())
                .aaAssets(aaAssets)
                .createdDay(LocalDate.now())
                .startDay(aaDTO.getStartDay())
                .endDay(aaDTO.getEndDay())
                .initialCash(aaDTO.getInitialCash())
                .rebalancingPeriod(aaDTO.getRebalancingPeriod())
                .member(member)
                .strategyType(daaDTO.getStrategyType())
                .build()
                .addAaAssetList(aaAssets);
    }


    @Override
    public Aa updateFromDTO(AaDTO daaDTO, List<AaAsset> aaAssets) {
        this.setName(daaDTO.getName());
        this.setLastModifiedDay(LocalDate.now());
        this.setStartDay(daaDTO.getStartDay());
        this.setEndDay(daaDTO.getEndDay());
        this.setInitialCash(daaDTO.getInitialCash());
        this.setRebalancingPeriod(daaDTO.getRebalancingPeriod());
        this.setStrategyType((((DaaDTO) daaDTO).getStrategyType()));

        this.getAaAssets().clear();
        this.getAaAssets().addAll(aaAssets);
        this.addAaAssetList(aaAssets);

        return this;
    }

    public AaDTO toDTO(){
        return DaaDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .startDay(this.getStartDay())
                .endDay(this.getEndDay())
                .rebalancingPeriod(this.getRebalancingPeriod())
                .initialCash(this.getInitialCash())
                .lastModifiedDay(this.getLastModifiedDay())
                .createdDay(this.getCreatedDay())
                .aaAssets(this.getAaAssets().stream()
                        .map(AaAsset::toDTO)
                        .collect(Collectors.toList()))
                .strategyType(this.strategyType)
                .build();
    }

    @Override
    public String type() {
        return "dynamic";
    }
}
