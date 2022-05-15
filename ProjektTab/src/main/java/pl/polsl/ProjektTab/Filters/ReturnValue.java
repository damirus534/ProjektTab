package pl.polsl.ProjektTab.Filters;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReturnValue {
    private Long id;
    private String description;
    private String productName;
    private String photoUrl;
    private Float prize;
}
