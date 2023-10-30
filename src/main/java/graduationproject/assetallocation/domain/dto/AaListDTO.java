package graduationproject.assetallocation.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter @Setter
public class AaListDTO {
    Long id;
    String name;
    LocalDate createdDay;
    String type;
}
