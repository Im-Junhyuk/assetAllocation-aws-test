package graduationproject.assetallocation.service;

import com.sun.jdi.request.DuplicateRequestException;
import graduationproject.assetallocation.domain.Authority;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.dto.MemberDTO;
import graduationproject.assetallocation.domain.dto.MemberListDTO;
import graduationproject.assetallocation.exception.NotFoundMemberException;
import graduationproject.assetallocation.repository.MemberRepository;
import graduationproject.assetallocation.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signup(MemberDTO memberDTO){
        if(memberRepository.findByLoginId(memberDTO.getLoginId()).orElse(null) != null){
            throw new DuplicateRequestException("There is a same name.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .loginId(memberDTO.getLoginId())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .authorities(Collections.singleton(authority))
                .joinDay(LocalDateTime.now())
                .build();

        return memberRepository.save(member);
    }

    @Transactional
    public void signupAdmin(MemberDTO memberDTO){
        if(memberRepository.findByLoginId(memberDTO.getLoginId()).orElse(null) != null){
            throw new DuplicateRequestException("There is a same name.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_ADMIN")
                .build();

        Member member = Member.builder()
                .loginId(memberDTO.getLoginId())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .authorities(Collections.singleton(authority))
                .joinDay(LocalDateTime.now())
                .build();

        memberRepository.save(member);
    }
    @Transactional(readOnly = true)
    public MemberDTO getMemberWithAuthorities(String loginId){
        return MemberDTO.from(memberRepository.findOneWithAuthoritiesByLoginId(loginId).orElse(null));
    }

    @Transactional(readOnly = true)
    public MemberDTO getMyMemberWithAuthorities(){
        return MemberDTO.from(
                SecurityUtil.getCurrentLoginId()
                        .flatMap(memberRepository::findOneWithAuthoritiesByLoginId)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }

    @Transactional(readOnly = true)
    public List<MemberListDTO> getAllMember(){
        List<Member> members = memberRepository.findAll();

        List<MemberListDTO> memberListDTOS = new ArrayList<>();
        for (Member member : members){
            memberListDTOS.add(MemberListDTO.from(member));
        }
        return memberListDTOS;
    }

    public void saveMember(Member member){

        memberRepository.save(member);
    }

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public Optional<Member> findByLoginId(String loginId){
        return memberRepository.findByLoginId(loginId);
    }
    public void deleteMember(Long memberId){

        memberRepository.deleteById(memberId);
    }


}
