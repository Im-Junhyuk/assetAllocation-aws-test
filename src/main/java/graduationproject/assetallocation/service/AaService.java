package graduationproject.assetallocation.service;

import graduationproject.assetallocation.domain.AaAsset;
import graduationproject.assetallocation.domain.Asset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.aa.Aa;
import graduationproject.assetallocation.domain.aa.Daa;
import graduationproject.assetallocation.domain.aa.Saa;
import graduationproject.assetallocation.domain.dto.*;
import graduationproject.assetallocation.repository.AaAssetRepository;
import graduationproject.assetallocation.repository.AaRepository;
import graduationproject.assetallocation.repository.AssetRepository;
import graduationproject.assetallocation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class AaService {
    private final AaRepository aaRepository;
    private final AaAssetRepository aaAssetRepository;
    private final AssetRepository assetRepository;
    private final MemberRepository memberRepository;


    public void deleteById(Long aAId){
        aaRepository.deleteById(aAId);
    }

    public Optional<Aa> findById(Long aAId){
        return aaRepository.findOneWithAaAssetsById(aAId);
    }

    public List<Aa> findByMember(Member member){ return aaRepository.findByMember(member);}

    public void deleteByMember(Member member){ aaRepository.deleteByMember(member);}

    public List<Aa> findAll(){ return aaRepository.findAll();}


    private List<AaAsset> createAaAssetList(List<AaAssetDTO> aaAssetDTOList){
        List<AaAsset> aaAssets = new ArrayList<>();
        for(AaAssetDTO aaAssetDTO:aaAssetDTOList){
            Asset asset = assetRepository.findByName(aaAssetDTO.getAssetName()).get();
            AaAsset aaAsset = AaAsset.createAaAsset(asset, aaAssetDTO.getRate());
            aaAssets.add(aaAsset);
        }
        return aaAssets;
    }

    public Long createSaa(String name, Member member, List<AaAssetDTO> aaAssetDTOs, String startDay,
                          String endDay, Long initialCash, RebalancingPeriod rebalancingPeriod){

        log.info("AAService createSAA");
        // create AAAsset
        List<AaAsset> aaAssets = createAaAssetList(aaAssetDTOs);


        // create AA
        Aa aA = Saa.createSaa(name, member, aaAssets, startDay, endDay, initialCash, rebalancingPeriod);

        return aaRepository.save(aA).getId();
    }

    //@Transactional
    public AaDTO updateSaa(SaaDTO saaDTO) {

        Saa saa = (Saa) aaRepository.findById(saaDTO.getId()).get();

        //update AaAsset
        List<AaAsset> aaAssetList = createAaAssetList(saaDTO.getAaAssets());

        //update saa
        Aa updatesaa = saa.updateFromDTO(saaDTO, aaAssetList);

        return SaaDTO.from(aaRepository.save(updatesaa));
    }

    public AaDTO createDaa(Long memberId, AaDTO aaDTO){
        // find member
        Member member = memberRepository.findById(memberId).get();

        // aaAsset
        List<AaAsset> aaAssetList= createAaAssetList(aaDTO.getAaAssets());

        // aa
        Daa daa = (Daa) Daa.createDaa(aaDTO, aaAssetList, member);

        // save and return
        return aaRepository.save(daa).toDTO();
    }

    public DaaDTO updateDaa(DaaDTO daaDTO) {
        return null;
    }

    public AaDTO updateAa(AaDTO aaDTO) {

        Aa aa = aaRepository.findById(aaDTO.getId()).get();

        //update AaAsset
        List<AaAsset> aaAssetList = createAaAssetList(aaDTO.getAaAssets());

        //update saa
        Aa updateAa = aa.updateFromDTO(aaDTO, aaAssetList);

        return aaRepository.save(updateAa).toDTO();
    }

    public List<AaListDTO> findAaListById(Long memberId){
        return aaRepository.findAllByMember(memberRepository.findById(memberId).get())
                .stream()
                .map((s) -> s.toListDTO())
                .collect(Collectors.toList());
    }
}
