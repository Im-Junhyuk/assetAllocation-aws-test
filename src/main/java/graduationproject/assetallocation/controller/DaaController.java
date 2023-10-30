package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.aa.Aa;
import graduationproject.assetallocation.domain.aa.Saa;
import graduationproject.assetallocation.domain.dto.AaDTO;
import graduationproject.assetallocation.domain.dto.DaaDTO;
import graduationproject.assetallocation.domain.dto.SaaDTO;
import graduationproject.assetallocation.service.AaService;
import graduationproject.assetallocation.service.MemberService;
import graduationproject.assetallocation.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DaaController {
    private final AaService aaService;
    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    // save
    @PostMapping("/user/daa")
    public AaDTO createDaa(@RequestBody DaaDTO daaDTO,
                           @RequestHeader("Authorization") String auth){

        return aaService.createDaa(jwtUtil.getIdFromAuth(auth), daaDTO);
    }

    //find
    @GetMapping("/user/daa/{daaId}")
    public ResponseEntity<AaDTO> findOneById(@PathVariable Long daaId,
                                             @RequestHeader("Authorization") String auth) {
        Aa daa = aaService.findById(daaId).get();
        // check authorization
        if (isaBoolean(daaId, auth))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

//        String token = jwtUtil.getToken(auth);
//        if (aa.getMember().getId() != jwtUtil.extractId(token))
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        return ResponseEntity.ok(daa.toDTO());
    }
    //delete
    @DeleteMapping("/user/daa/{daaId}")
    public ResponseEntity<String> deleteOneById(@PathVariable Long daaId,
                                                @RequestHeader("Authorization") String auth) {
        // check auth
        if (isaBoolean(daaId, auth))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");

        aaService.deleteById(daaId);
        return ResponseEntity.ok("success");
    }

    //update
    @PutMapping("/user/daa/{daaId}")
    public ResponseEntity<AaDTO> updateOne(@PathVariable Long daaId,
                                           @RequestBody DaaDTO daaDTO,
                                           @RequestHeader("Authorization") String auth){

        log.info("daa update id={}", daaDTO.getId());
        //check auth
        if (isaBoolean(daaId, auth))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        // update saa
        return ResponseEntity.ok(aaService.updateAa(daaDTO));
    }

    //find all

    private boolean isaBoolean(Long saaId, String auth) {
        return !Objects.equals(aaService.findById(saaId).get().getMember().getId(), jwtUtil.getIdFromAuth(auth));
    }

}
