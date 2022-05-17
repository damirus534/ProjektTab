package pl.polsl.ProjektTab.Filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductOff {
    Long id;
    Integer amountAvailable;
    String size;
}
