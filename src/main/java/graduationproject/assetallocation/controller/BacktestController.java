package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.aa.Aa;
import graduationproject.assetallocation.domain.dto.DaaDTO;
import graduationproject.assetallocation.domain.dto.SaaDTO;
import graduationproject.assetallocation.service.AaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BacktestController {

    private final AaService aaService;

    @PostMapping("/backtest_static")
    public String SaaBacktest(@RequestBody SaaDTO saaDTO){

        return "ok";
    }

    @PostMapping("/backtest_dynamic")
    public String DaaBacktest(@RequestBody DaaDTO daaDTO){

        return "ok";
    }

}
