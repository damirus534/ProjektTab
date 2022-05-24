package pl.polsl.ProjektTab.Filters;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@AllArgsConstructor
@Data
public class UserRaportFilterReqBody {
    private Date beginning;
    private Date ending;

}
