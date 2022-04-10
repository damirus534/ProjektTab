package pl.polsl.ProjektTab.Model;

import lombok.Data;

import javax.persistence.*;

@Data
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
    @JoinColumn(referencedColumnName = "id_info", nullable = false)
    private ProduktInfo produktInfoByIdInfo;

}
