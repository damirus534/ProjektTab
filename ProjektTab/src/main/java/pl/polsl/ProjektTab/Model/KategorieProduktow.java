package pl.polsl.ProjektTab.Model;

import javax.persistence.*;
import java.util.Collection;

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

    public int getIdKategorii() {
        return idKategorii;
    }

    public void setIdKategorii(int idKategorii) {
        this.idKategorii = idKategorii;
    }

    public String getNazwaKategorii() {
        return nazwaKategorii;
    }

    public void setNazwaKategorii(String nazwaKategorii) {
        this.nazwaKategorii = nazwaKategorii;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KategorieProduktow that = (KategorieProduktow) o;

        if (idKategorii != that.idKategorii) return false;
        if (nazwaKategorii != null ? !nazwaKategorii.equals(that.nazwaKategorii) : that.nazwaKategorii != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idKategorii;
        result = 31 * result + (nazwaKategorii != null ? nazwaKategorii.hashCode() : 0);
        return result;
    }

    public Collection<ProduktInfo> getProduktInfosByIdKategorii() {
        return produktInfosByIdKategorii;
    }

    public void setProduktInfosByIdKategorii(Collection<ProduktInfo> produktInfosByIdKategorii) {
        this.produktInfosByIdKategorii = produktInfosByIdKategorii;
    }
}
