package graduationproject.assetallocation.repository;

import graduationproject.assetallocation.domain.AaAsset;
import graduationproject.assetallocation.domain.aa.Aa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AaAssetRepository extends JpaRepository<AaAsset, Long> {
    void deleteById(Long id);
    void deleteByAa(Aa aa);
}
