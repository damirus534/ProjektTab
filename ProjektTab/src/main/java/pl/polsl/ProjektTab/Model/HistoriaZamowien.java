package pl.polsl.ProjektTab.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "historia zamowien", schema = "mydb", catalog = "")
public class HistoriaZamowien {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rekordu_historii")
    private int idRekorduHistorii;
    @Basic
    @Column(name = "id_uzytkownika")
    private int idUzytkownika;
    @Basic
    @Column(name = "data")
    private Timestamp data;
    @Basic
    @Column(name = "suma_zakupu")
    private double sumaZakupu;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id_uzytkownika", nullable = false)
    private Uzytkownicy uzytkownicyByIdUzytkownika;
    @OneToMany(mappedBy = "historiaZamowienByIdRekorduHistorii")
    private Collection<Zamowienia> zamowieniasByIdRekorduHistorii;

}
