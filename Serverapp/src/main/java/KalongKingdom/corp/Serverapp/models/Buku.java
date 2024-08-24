package KalongKingdom.corp.Serverapp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Buku")
public class Buku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_buku")
    private Integer id;

    @OneToMany(mappedBy = "buku", cascade = CascadeType.ALL)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Peminjaman> peminjaman;

    @Column(name = "judul_buku", nullable = false, length = 500)
    private String judul;

    @Column(name = "pengarang", nullable = false, length = 200)
    private String pengarang;

    @Column(name = "penerbit", nullable = false, length = 50)
    private String penerbit;

    @Column(name = "tahun_tebit", nullable = false, length = 4)
    private String tahunTerbit;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "sinopsis", nullable = false, length = 1000)
    private String sinopsis;

    @ManyToOne
    @JoinColumn(name = "kategori_id", nullable = false)
    private Kategori kategori;

    @Column(name = "image_buku", nullable = false, columnDefinition = "LONGTEXT")
    private String image;
}