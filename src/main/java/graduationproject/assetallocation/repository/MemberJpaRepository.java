package graduationproject.assetallocation.repository;

import graduationproject.assetallocation.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository{

    private EntityManager em;

    public void create(Member member){
        em.persist(member);
    }

    public Member findById(Long id){
        return em.find(Member.class, id);
    }
    public void delete(Long id){
        em.remove(findById(id));
    }
}
