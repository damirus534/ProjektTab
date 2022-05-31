package pl.polsl.ProjektTab.Filters;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class AdminRaportFilterReqBody {
    Long categoryId;
    Date beginning;
    Date ending;
    Integer raportType;

    public AdminRaportFilterReqBody(Long categoryId, Date beginning, Date ending, Integer raportType) {
        this.categoryId = categoryId;
        this.beginning = beginning;
        this.ending = ending;
        this.raportType = raportType;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Integer getRaportType() {
        return this.raportType;
    }

    public void setRaportType(Integer raportType) {
        this.raportType = raportType;
    }
}
