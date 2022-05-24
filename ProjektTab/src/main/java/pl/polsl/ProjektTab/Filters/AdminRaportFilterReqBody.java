package pl.polsl.ProjektTab.Filters;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AdminRaportFilterReqBody {
    Long categoryId;
    Date beginning;
    Date ending;
    Integer raportType;
}
