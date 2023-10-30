package graduationproject.assetallocation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Asset {

    @Id @GeneratedValue
    @Column(name = "ASSET_ID")
    private Long id;

    private String name;

    public Asset(String assetName){
        this.name = assetName;
    }
}
