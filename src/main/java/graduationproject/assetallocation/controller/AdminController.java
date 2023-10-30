package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.Asset;
import graduationproject.assetallocation.domain.dto.MemberDTO;
import graduationproject.assetallocation.domain.dto.MemberListDTO;
import graduationproject.assetallocation.repository.AssetRepository;
import graduationproject.assetallocation.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AssetRepository assetRepository;
    private final MemberService memberService;

    @GetMapping("/admin/assets")
    @PreAuthorize("hasRole('ADMIN')")
    List<Asset> findAll(){
        log.info("findAllAsset");
        return assetRepository.findAll();
    }

    @GetMapping("/admin/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MemberDTO> getMemberInfo(@PathVariable String userId){
        return ResponseEntity.ok(memberService.getMemberWithAuthorities(userId));
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<MemberListDTO>> getAllMember(){
        return ResponseEntity.ok(memberService.getAllMember());
    }
}
