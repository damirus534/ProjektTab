package pl.polsl.ProjektTab.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
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

}
