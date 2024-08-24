package KalongKingdom.corp.clientapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guest {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String image;
    private User user;
    private List<Peminjaman> peminjaman;

}
