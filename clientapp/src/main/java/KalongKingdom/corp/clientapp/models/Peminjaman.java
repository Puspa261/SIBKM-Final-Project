package KalongKingdom.corp.clientapp.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peminjaman {

    private Integer id;
    private Date pinjam;
    private Date kembali;
    private Buku buku;
    private Guest guest;
    private Status status;

}
