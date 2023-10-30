package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.dto.AaListDTO;
import graduationproject.assetallocation.service.AaService;
import graduationproject.assetallocation.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AaController {
    private final AaService aaService;
    private final JwtUtil jwtUtil;

    @GetMapping("/user/aas")
    public ResponseEntity<List<AaListDTO>> findAaListById(@RequestHeader("Authorization") String auth){

//        if (isaBoolean(saaId, auth))
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        return ResponseEntity.ok(aaService.findAll().stream()
//                .map(s -> s.toListDTO())
//                .collect(Collectors.toList()));
        return ResponseEntity.ok(aaService.findAaListById(jwtUtil.getIdFromAuth(auth)));
    }

    private boolean isaBoolean(Long saaId, String auth) {
        return !Objects.equals(aaService.findById(saaId).get().getMember().getId(), jwtUtil.getIdFromAuth(auth));
    }
}
