package pl.polsl.ProjektTab.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Koszyki {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_koszyka")
    private int idKoszyka;
    @Basic
    @Column(name = "id_uzytkownika")
    private int idUzytkownika;
    @Basic
    @Column(name = "id_produktu")
    private int idProduktu;
    @Basic
    @Column(name = "ilosc_produktow")
    private int iloscProduktow;
    @ManyToOne
    @JoinColumn(name = "id_uzytkownika", referencedColumnName = "id_uzytkownika", nullable = false)
    private Uzytkownicy uzytkownicyByIdUzytkownika;
    @ManyToOne
    @JoinColumn(name = "id_produktu", referencedColumnName = "id_produktu", nullable = false)
    private Produkt produktByIdProduktu;

}
