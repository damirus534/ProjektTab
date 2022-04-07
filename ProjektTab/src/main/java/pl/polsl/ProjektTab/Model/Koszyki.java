package pl.polsl.ProjektTab.Model;

import javax.persistence.*;

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

    public int getIdKoszyka() {
        return idKoszyka;
    }

    public void setIdKoszyka(int idKoszyka) {
        this.idKoszyka = idKoszyka;
    }

    public int getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(int idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public int getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(int idProduktu) {
        this.idProduktu = idProduktu;
    }

    public int getIloscProduktow() {
        return iloscProduktow;
    }

    public void setIloscProduktow(int iloscProduktow) {
        this.iloscProduktow = iloscProduktow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Koszyki koszyki = (Koszyki) o;

        if (idKoszyka != koszyki.idKoszyka) return false;
        if (idUzytkownika != koszyki.idUzytkownika) return false;
        if (idProduktu != koszyki.idProduktu) return false;
        if (iloscProduktow != koszyki.iloscProduktow) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idKoszyka;
        result = 31 * result + idUzytkownika;
        result = 31 * result + idProduktu;
        result = 31 * result + iloscProduktow;
        return result;
    }

    public Uzytkownicy getUzytkownicyByIdUzytkownika() {
        return uzytkownicyByIdUzytkownika;
    }

    public void setUzytkownicyByIdUzytkownika(Uzytkownicy uzytkownicyByIdUzytkownika) {
        this.uzytkownicyByIdUzytkownika = uzytkownicyByIdUzytkownika;
    }

    public Produkt getProduktByIdProduktu() {
        return produktByIdProduktu;
    }

    public void setProduktByIdProduktu(Produkt produktByIdProduktu) {
        this.produktByIdProduktu = produktByIdProduktu;
    }
}
