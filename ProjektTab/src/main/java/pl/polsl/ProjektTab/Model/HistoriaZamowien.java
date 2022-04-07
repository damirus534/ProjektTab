package pl.polsl.ProjektTab.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "historia zamowien", schema = "mydb", catalog = "")
public class HistoriaZamowien {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rekordu_historii")
    private int idRekorduHistorii;
    @Basic
    @Column(name = "id_uzytkownika")
    private int idUzytkownika;
    @Basic
    @Column(name = "data")
    private Timestamp data;
    @Basic
    @Column(name = "suma_zakupu")
    private double sumaZakupu;
    @ManyToOne
    @JoinColumn(name = "id_uzytkownika", referencedColumnName = "id_uzytkownika", nullable = false)
    private Uzytkownicy uzytkownicyByIdUzytkownika;
    @OneToMany(mappedBy = "historiaZamowienByIdRekorduHistorii")
    private Collection<Zamowienia> zamowieniasByIdRekorduHistorii;

    public int getIdRekorduHistorii() {
        return idRekorduHistorii;
    }

    public void setIdRekorduHistorii(int idRekorduHistorii) {
        this.idRekorduHistorii = idRekorduHistorii;
    }

    public int getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(int idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public double getSumaZakupu() {
        return sumaZakupu;
    }

    public void setSumaZakupu(double sumaZakupu) {
        this.sumaZakupu = sumaZakupu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoriaZamowien that = (HistoriaZamowien) o;

        if (idRekorduHistorii != that.idRekorduHistorii) return false;
        if (idUzytkownika != that.idUzytkownika) return false;
        if (Double.compare(that.sumaZakupu, sumaZakupu) != 0) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idRekorduHistorii;
        result = 31 * result + idUzytkownika;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        temp = Double.doubleToLongBits(sumaZakupu);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Uzytkownicy getUzytkownicyByIdUzytkownika() {
        return uzytkownicyByIdUzytkownika;
    }

    public void setUzytkownicyByIdUzytkownika(Uzytkownicy uzytkownicyByIdUzytkownika) {
        this.uzytkownicyByIdUzytkownika = uzytkownicyByIdUzytkownika;
    }

    public Collection<Zamowienia> getZamowieniasByIdRekorduHistorii() {
        return zamowieniasByIdRekorduHistorii;
    }

    public void setZamowieniasByIdRekorduHistorii(Collection<Zamowienia> zamowieniasByIdRekorduHistorii) {
        this.zamowieniasByIdRekorduHistorii = zamowieniasByIdRekorduHistorii;
    }
}
