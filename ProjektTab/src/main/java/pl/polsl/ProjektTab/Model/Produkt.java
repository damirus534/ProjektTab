package pl.polsl.ProjektTab.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
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

}
