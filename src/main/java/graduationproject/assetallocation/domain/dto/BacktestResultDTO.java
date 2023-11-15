package graduationproject.assetallocation.domain.dto;

import lombok.*;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BacktestResultDTO {

    Double returns;
    Double volatility;
    Double sharpe;
    String graph;
}
//"returns": ,
//        "volatility": ,
//        "sharpe": ,
//        "graph":"base64_encoded_string"