package KalongKingdom.corp.clientapp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestRequest {
    
    private String name;
    private String email;
    private String phone;
    private String image;
    private String username;
    private String password;
}
