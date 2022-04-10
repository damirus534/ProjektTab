package pl.polsl.ProjektTab.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "kategorie produktow", schema = "mydb", catalog = "")
public class KategorieProduktow {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_kategorii")
    private int idKategorii;
    @Basic
    @Column(name = "nazwa_kategorii")
    private String nazwaKategorii;
    @OneToMany(mappedBy = "kategorieProduktowByIdKategorii")
    private Collection<ProduktInfo> produktInfosByIdKategorii;


}
