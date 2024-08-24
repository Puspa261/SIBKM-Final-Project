package KalongKingdom.corp.clientapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kategori {

    private Integer id;
    private String name;
    private List<Buku> buku;
}
