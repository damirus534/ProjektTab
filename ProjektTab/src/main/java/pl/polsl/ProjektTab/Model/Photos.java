package pl.polsl.ProjektTab.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Photos {
    private Long photoId;
    private Long productInfoId;
    private String photoUrl;
}
