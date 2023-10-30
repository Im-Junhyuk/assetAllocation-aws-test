package graduationproject.assetallocation.domain.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DaaDTO extends AaDTO{
    private String strategyType;
}
