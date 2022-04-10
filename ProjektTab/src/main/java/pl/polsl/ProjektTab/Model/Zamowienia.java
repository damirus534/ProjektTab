package pl.polsl.ProjektTab.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
    @JoinColumn(referencedColumnName = "id_produktu", nullable = false)
    private Produkt produktByIdProduktu;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id_rekordu_historii", nullable = false)
    private HistoriaZamowien historiaZamowienByIdRekorduHistorii;

}
