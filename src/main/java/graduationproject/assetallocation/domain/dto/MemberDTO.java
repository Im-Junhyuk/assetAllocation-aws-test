package graduationproject.assetallocation.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import graduationproject.assetallocation.domain.Authority;
import graduationproject.assetallocation.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MemberDTO {

    private String loginId;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Set<Authority> authoritySet;

    public static MemberDTO from(Member member){
        if (member == null) return null;

        return MemberDTO.builder()
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .authoritySet(member.getAuthorities())
                .build();
    }
}
