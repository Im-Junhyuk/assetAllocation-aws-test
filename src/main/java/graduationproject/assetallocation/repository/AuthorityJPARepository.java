package graduationproject.assetallocation.repository;

import graduationproject.assetallocation.domain.Authority;
import graduationproject.assetallocation.domain.aa.Aa;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class AuthorityJPARepository {

    private final EntityManager em;

    @Transactional
    public void save(Authority authority){
        if(authority.getAuthorityName() == null) {
            em.persist(authority);
        }
        else {
            em.merge(authority);
        }
    }
}
