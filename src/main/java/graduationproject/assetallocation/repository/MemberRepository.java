package graduationproject.assetallocation.repository;

import graduationproject.assetallocation.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    void deleteById(Long id);

    Optional<Member> findById(Long id);

    Optional<Member> findByLoginId(String loginId);

    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findOneWithAuthoritiesByLoginId(String loginId);

    List<Member> findAll();
}
