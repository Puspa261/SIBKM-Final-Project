package KalongKingdom.corp.Serverapp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "peminjaman")
public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tgl_pinjam", nullable = false)
    private Date pinjam;

    @Column(name = "tgl_kembali", nullable = false)
    private Date kembali;

    @ManyToOne
    @JoinColumn(name = "id_buku")
    // @JsonProperty(access = Access.WRITE_ONLY)
    @JsonIgnoreProperties("buku")
    private Buku buku;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    // @JsonProperty(access = Access.WRITE_ONLY)
    @JsonIgnoreProperties("peminjamans")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
}
