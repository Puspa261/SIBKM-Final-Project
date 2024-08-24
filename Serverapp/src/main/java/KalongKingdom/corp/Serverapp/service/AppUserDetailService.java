package KalongKingdom.corp.Serverapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import KalongKingdom.corp.Serverapp.models.AppUserDetail;
import KalongKingdom.corp.Serverapp.models.User;
import KalongKingdom.corp.Serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService{
    private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    User user = userRepository
      .findByUsernameOrGuestEmail(username, username)
      .orElseThrow(() ->
        new UsernameNotFoundException("Username or Email not found!!!")
      );

    return new AppUserDetail(user);
  }
}
