package graduationproject.assetallocation.domain;

import graduationproject.assetallocation.domain.aa.Aa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
public class Member {
    // id, login_id, password, 가입일, 수정일
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aa> aaList = new ArrayList<>();

    private String password;

    private LocalDateTime joinDay;

    @ManyToMany
    @JoinTable(
            name = "MEMBER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_NAME", referencedColumnName = "AUTHORITY_NAME")})
    private Set<Authority> authorities;

    public Member(){
        this.setJoinDay(LocalDateTime.now());
    }
}
