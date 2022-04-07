package pl.polsl.ProjektTab.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Produkt {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_produktu")
    private int idProduktu;
    @Basic
    @Column(name = "id_info")
    private int idInfo;
    @Basic
    @Column(name = "rozmiar")
    private String rozmiar;
    @Basic
    @Column(name = "ilosc_dostepnych")
    private int iloscDostepnych;
    @OneToMany(mappedBy = "produktByIdProduktu")
    private Collection<Koszyki> koszykisByIdProduktu;
    @ManyToOne
    @JoinColumn(name = "id_info", referencedColumnName = "id_info", nullable = false)
    private ProduktInfo produktInfoByIdInfo;
    @OneToMany(mappedBy = "produktByIdProduktu")
    private Collection<Zamowienia> zamowieniasByIdProduktu;

    public int getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(int idProduktu) {
        this.idProduktu = idProduktu;
    }

    public int getIdInfo() {
        return idInfo;
    }

    public void setIdInfo(int idInfo) {
        this.idInfo = idInfo;
    }

    public String getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(String rozmiar) {
        this.rozmiar = rozmiar;
    }

    public int getIloscDostepnych() {
        return iloscDostepnych;
    }

    public void setIloscDostepnych(int iloscDostepnych) {
        this.iloscDostepnych = iloscDostepnych;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produkt produkt = (Produkt) o;

        if (idProduktu != produkt.idProduktu) return false;
        if (idInfo != produkt.idInfo) return false;
        if (iloscDostepnych != produkt.iloscDostepnych) return false;
        if (rozmiar != null ? !rozmiar.equals(produkt.rozmiar) : produkt.rozmiar != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProduktu;
        result = 31 * result + idInfo;
        result = 31 * result + (rozmiar != null ? rozmiar.hashCode() : 0);
        result = 31 * result + iloscDostepnych;
        return result;
    }

    public Collection<Koszyki> getKoszykisByIdProduktu() {
        return koszykisByIdProduktu;
    }

    public void setKoszykisByIdProduktu(Collection<Koszyki> koszykisByIdProduktu) {
        this.koszykisByIdProduktu = koszykisByIdProduktu;
    }

    public ProduktInfo getProduktInfoByIdInfo() {
        return produktInfoByIdInfo;
    }

    public void setProduktInfoByIdInfo(ProduktInfo produktInfoByIdInfo) {
        this.produktInfoByIdInfo = produktInfoByIdInfo;
    }

    public Collection<Zamowienia> getZamowieniasByIdProduktu() {
        return zamowieniasByIdProduktu;
    }

    public void setZamowieniasByIdProduktu(Collection<Zamowienia> zamowieniasByIdProduktu) {
        this.zamowieniasByIdProduktu = zamowieniasByIdProduktu;
    }
}
