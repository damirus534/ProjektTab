package pl.polsl.ProjektTab.Model;

import javax.persistence.*;

@Entity
public class Zamowienia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_zamownienia")
    private int idZamownienia;
    @Basic
    @Column(name = "id_produktu")
    private int idProduktu;
    @Basic
    @Column(name = "id_rekordu_historii")
    private int idRekorduHistorii;
    @Basic
    @Column(name = "ilosc_zakupionych")
    private int iloscZakupionych;
    @Basic
    @Column(name = "cena_sprzedazy")
    private double cenaSprzedazy;
    @ManyToOne
    @JoinColumn(name = "id_produktu", referencedColumnName = "id_produktu", nullable = false)
    private Produkt produktByIdProduktu;
    @ManyToOne
    @JoinColumn(name = "id_rekordu_historii", referencedColumnName = "id_rekordu_historii", nullable = false)
    private HistoriaZamowien historiaZamowienByIdRekorduHistorii;

    public int getIdZamownienia() {
        return idZamownienia;
    }

    public void setIdZamownienia(int idZamownienia) {
        this.idZamownienia = idZamownienia;
    }

    public int getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(int idProduktu) {
        this.idProduktu = idProduktu;
    }

    public int getIdRekorduHistorii() {
        return idRekorduHistorii;
    }

    public void setIdRekorduHistorii(int idRekorduHistorii) {
        this.idRekorduHistorii = idRekorduHistorii;
    }

    public int getIloscZakupionych() {
        return iloscZakupionych;
    }

    public void setIloscZakupionych(int iloscZakupionych) {
        this.iloscZakupionych = iloscZakupionych;
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

        Zamowienia that = (Zamowienia) o;

        if (idZamownienia != that.idZamownienia) return false;
        if (idProduktu != that.idProduktu) return false;
        if (idRekorduHistorii != that.idRekorduHistorii) return false;
        if (iloscZakupionych != that.iloscZakupionych) return false;
        if (Double.compare(that.cenaSprzedazy, cenaSprzedazy) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idZamownienia;
        result = 31 * result + idProduktu;
        result = 31 * result + idRekorduHistorii;
        result = 31 * result + iloscZakupionych;
        temp = Double.doubleToLongBits(cenaSprzedazy);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Produkt getProduktByIdProduktu() {
        return produktByIdProduktu;
    }

    public void setProduktByIdProduktu(Produkt produktByIdProduktu) {
        this.produktByIdProduktu = produktByIdProduktu;
    }

    public HistoriaZamowien getHistoriaZamowienByIdRekorduHistorii() {
        return historiaZamowienByIdRekorduHistorii;
    }

    public void setHistoriaZamowienByIdRekorduHistorii(HistoriaZamowien historiaZamowienByIdRekorduHistorii) {
        this.historiaZamowienByIdRekorduHistorii = historiaZamowienByIdRekorduHistorii;
    }
}
