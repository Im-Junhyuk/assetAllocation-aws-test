package graduationproject.assetallocation.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class BacktestResultDTO {
    Long returns;

    Long volatility;

    Long sharpe;

    String graph;
}
//"returns": 정수값,
//        "volatility": 정수값,
//        "sharpe": 정수값,
//        "graph":"base64_encoded_string"