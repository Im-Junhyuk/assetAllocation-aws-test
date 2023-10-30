package graduationproject.assetallocation.repository;

import graduationproject.assetallocation.domain.aa.Aa;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AaJpaRepository {

    private final EntityManager em;

    public void save(Aa aA){
        if(aA.getId() == null) {
            em.persist(aA);
        }
        else {
            em.merge(aA);
        }
    }

    public void delete(Long aAId){
        em.remove(findById(aAId));
    }

    public Aa findById(Long aAId){
        return em.find(Aa.class, aAId);
    }
    // user_id를 어떻게 받는가?
//    public AA findByName(String name){
//        return findAA;
//    }
//
//    public List<AA> findAll(){
//
//    }
}
