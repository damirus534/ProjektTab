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

    public Date getBeginning() {
        return this.beginning;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public Date getEnding() {
        return this.ending;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }

}
