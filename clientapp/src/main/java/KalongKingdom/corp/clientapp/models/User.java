package KalongKingdom.corp.clientapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String password;
    private Boolean isEnabled;
    private Boolean isAccountNonLocked;
    private Guest guest;
    private List<Role> roles;
    
}
