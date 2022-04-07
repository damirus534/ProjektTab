package pl.polsl.ProjektTab.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "produkt info", schema = "mydb", catalog = "")
public class ProduktInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_info")
    private int idInfo;
    @Basic
    @Column(name = "id_kategorii")
    private int idKategorii;
    @Basic
    @Column(name = "nazwa_produktu")
    private String nazwaProduktu;
    @Basic
    @Column(name = "opis")
    private String opis;
    @Basic
    @Column(name = "cena_zakupu")
    private double cenaZakupu;
    @Basic
    @Column(name = "cena_sprzedazy")
    private double cenaSprzedazy;
    @OneToMany(mappedBy = "produktInfoByIdInfo")
    private Collection<Produkt> produktsByIdInfo;
    @ManyToOne
    @JoinColumn(name = "id_kategorii", referencedColumnName = "id_kategorii", nullable = false)
    private KategorieProduktow kategorieProduktowByIdKategorii;
    @OneToMany(mappedBy = "produktInfoByIdInfo")
    private Collection<Zdjecia> zdjeciasByIdInfo;

    public int getIdInfo() {
        return idInfo;
    }

    public void setIdInfo(int idInfo) {
        this.idInfo = idInfo;
    }

    public int getIdKategorii() {
        return idKategorii;
    }

    public void setIdKategorii(int idKategorii) {
        this.idKategorii = idKategorii;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCenaZakupu() {
        return cenaZakupu;
    }

    public void setCenaZakupu(double cenaZakupu) {
        this.cenaZakupu = cenaZakupu;
    }

    public double getCenaSprzedazy() {
        return cenaSprzedazy;
    }

    public void setCenaSprzedazy(double cenaSprzedazy) {
        this.cenaSprzedazy = cenaSprzedazy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProduktInfo that = (ProduktInfo) o;

        if (idInfo != that.idInfo) return false;
        if (idKategorii != that.idKategorii) return false;
        if (Double.compare(that.cenaZakupu, cenaZakupu) != 0) return false;
        if (Double.compare(that.cenaSprzedazy, cenaSprzedazy) != 0) return false;
        if (nazwaProduktu != null ? !nazwaProduktu.equals(that.nazwaProduktu) : that.nazwaProduktu != null)
            return false;
        if (opis != null ? !opis.equals(that.opis) : that.opis != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idInfo;
        result = 31 * result + idKategorii;
        result = 31 * result + (nazwaProduktu != null ? nazwaProduktu.hashCode() : 0);
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        temp = Double.doubleToLongBits(cenaZakupu);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cenaSprzedazy);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Collection<Produkt> getProduktsByIdInfo() {
        return produktsByIdInfo;
    }

    public void setProduktsByIdInfo(Collection<Produkt> produktsByIdInfo) {
        this.produktsByIdInfo = produktsByIdInfo;
    }

    public KategorieProduktow getKategorieProduktowByIdKategorii() {
        return kategorieProduktowByIdKategorii;
    }

    public void setKategorieProduktowByIdKategorii(KategorieProduktow kategorieProduktowByIdKategorii) {
        this.kategorieProduktowByIdKategorii = kategorieProduktowByIdKategorii;
    }

    public Collection<Zdjecia> getZdjeciasByIdInfo() {
        return zdjeciasByIdInfo;
    }

    public void setZdjeciasByIdInfo(Collection<Zdjecia> zdjeciasByIdInfo) {
        this.zdjeciasByIdInfo = zdjeciasByIdInfo;
    }
}
