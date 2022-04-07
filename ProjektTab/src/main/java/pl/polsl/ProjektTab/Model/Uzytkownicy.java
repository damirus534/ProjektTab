package pl.polsl.ProjektTab.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Uzytkownicy {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_uzytkownika")
    private int idUzytkownika;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "haslo")
    private String haslo;
    @Basic
    @Column(name = "adres")
    private String adres;
    @OneToMany(mappedBy = "uzytkownicyByIdUzytkownika")
    private Collection<HistoriaZamowien> historiaZamowiensByIdUzytkownika;
    @OneToMany(mappedBy = "uzytkownicyByIdUzytkownika")
    private Collection<Koszyki> koszykisByIdUzytkownika;

    public int getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(int idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Uzytkownicy that = (Uzytkownicy) o;

        if (idUzytkownika != that.idUzytkownika) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (haslo != null ? !haslo.equals(that.haslo) : that.haslo != null) return false;
        if (adres != null ? !adres.equals(that.adres) : that.adres != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUzytkownika;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (haslo != null ? haslo.hashCode() : 0);
        result = 31 * result + (adres != null ? adres.hashCode() : 0);
        return result;
    }

    public Collection<HistoriaZamowien> getHistoriaZamowiensByIdUzytkownika() {
        return historiaZamowiensByIdUzytkownika;
    }

    public void setHistoriaZamowiensByIdUzytkownika(Collection<HistoriaZamowien> historiaZamowiensByIdUzytkownika) {
        this.historiaZamowiensByIdUzytkownika = historiaZamowiensByIdUzytkownika;
    }

    public Collection<Koszyki> getKoszykisByIdUzytkownika() {
        return koszykisByIdUzytkownika;
    }

    public void setKoszykisByIdUzytkownika(Collection<Koszyki> koszykisByIdUzytkownika) {
        this.koszykisByIdUzytkownika = koszykisByIdUzytkownika;
    }
}
