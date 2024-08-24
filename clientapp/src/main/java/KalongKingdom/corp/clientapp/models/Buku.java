package KalongKingdom.corp.clientapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Buku {

    private Integer id;
    private List<Peminjaman> peminjaman;
    private String judul;
    private String pengarang;
    private String penerbit;
    private String tahunTerbit;
    private String isbn;
    private String sinopsis;
    private Kategori kategori;
    private String image;

}
