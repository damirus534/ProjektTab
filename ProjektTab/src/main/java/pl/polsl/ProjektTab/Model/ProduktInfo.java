package pl.polsl.ProjektTab.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
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
    @JoinColumn(referencedColumnName = "id_kategorii", nullable = false)
    private KategorieProduktow kategorieProduktowByIdKategorii;
    @OneToMany(mappedBy = "produktInfoByIdInfo")
    private Collection<Zdjecia> zdjeciasByIdInfo;

}
