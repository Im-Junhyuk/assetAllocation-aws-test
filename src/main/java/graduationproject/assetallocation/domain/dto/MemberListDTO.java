package graduationproject.assetallocation.domain.dto;

import graduationproject.assetallocation.domain.Authority;
import graduationproject.assetallocation.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@AllArgsConstructor
@Getter @Setter
public class MemberListDTO {
    private String loginId;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    public static MemberListDTO from(Member member){
        if (member == null) return null;

        return MemberListDTO.builder()
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .build();
    }
}
