package pl.polsl.ProjektTab.Model;

import javax.persistence.*;

@Entity
public class Zdjecia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_zdjecia")
    private int idZdjecia;
    @Basic
    @Column(name = "id_info")
    private int idInfo;
    @Basic
    @Column(name = "url_zdjecia")
    private String urlZdjecia;
    @ManyToOne
    @JoinColumn(name = "id_info", referencedColumnName = "id_info", nullable = false)
    private ProduktInfo produktInfoByIdInfo;

    public int getIdZdjecia() {
        return idZdjecia;
    }

    public void setIdZdjecia(int idZdjecia) {
        this.idZdjecia = idZdjecia;
    }

    public int getIdInfo() {
        return idInfo;
    }

    public void setIdInfo(int idInfo) {
        this.idInfo = idInfo;
    }

    public String getUrlZdjecia() {
        return urlZdjecia;
    }

    public void setUrlZdjecia(String urlZdjecia) {
        this.urlZdjecia = urlZdjecia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zdjecia zdjecia = (Zdjecia) o;

        if (idZdjecia != zdjecia.idZdjecia) return false;
        if (idInfo != zdjecia.idInfo) return false;
        if (urlZdjecia != null ? !urlZdjecia.equals(zdjecia.urlZdjecia) : zdjecia.urlZdjecia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idZdjecia;
        result = 31 * result + idInfo;
        result = 31 * result + (urlZdjecia != null ? urlZdjecia.hashCode() : 0);
        return result;
    }

    public ProduktInfo getProduktInfoByIdInfo() {
        return produktInfoByIdInfo;
    }

    public void setProduktInfoByIdInfo(ProduktInfo produktInfoByIdInfo) {
        this.produktInfoByIdInfo = produktInfoByIdInfo;
    }
}
