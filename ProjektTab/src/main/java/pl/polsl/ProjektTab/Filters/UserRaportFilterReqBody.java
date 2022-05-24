package pl.polsl.ProjektTab.Filters;

import lombok.Data;

import java.util.Date;

@Data
public class UserRaportFilterReqBody {
    private Date beginning;
    private Date ending;

    public UserRaportFilterReqBody(Date beginning, Date ending) {
        this.beginning = beginning;
        this.ending = ending;
    }
}
