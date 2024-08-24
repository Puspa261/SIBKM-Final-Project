package KalongKingdom.corp.Serverapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import KalongKingdom.corp.Serverapp.models.Guest;
import KalongKingdom.corp.Serverapp.models.Role;
import KalongKingdom.corp.Serverapp.models.User;
import KalongKingdom.corp.Serverapp.models.dto.request.GuestRequest;
import KalongKingdom.corp.Serverapp.models.dto.request.LoginRequest;
import KalongKingdom.corp.Serverapp.models.dto.response.LoginResponse;
import KalongKingdom.corp.Serverapp.repositories.GuestRepository;
import KalongKingdom.corp.Serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
        private GuestRepository guestRepository;
        private ModelMapper modelMapper;
        private RoleService roleService;
        private PasswordEncoder passwordEncoder;
        private AuthenticationManager authenticationManager;
        private UserRepository userRepository;
        private AppUserDetailService appUserDetailService;

        public Guest registration(GuestRequest guestRequest) {
                Guest guest = modelMapper.map(guestRequest, Guest.class);
                User user = modelMapper.map(guestRequest, User.class);

                // set password
                user.setPassword(passwordEncoder.encode(guestRequest.getPassword()));

                // set default role
                List<Role> roles = new ArrayList<>();
                roles.add(roleService.getById(2));
                user.setRoles(roles);
                guest.setUser(user);
                user.setGuest(guest);
                return guestRepository.save(guest);
        }

        public LoginResponse login(LoginRequest loginRequest) {
                // set login request
                UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword());
                // set principle
                Authentication auth = authenticationManager.authenticate(authReq);
                SecurityContextHolder.getContext().setAuthentication(auth);

                // set login response
                User user = userRepository
                                .findByUsernameOrGuestEmail(
                                                loginRequest.getUsername(),
                                                loginRequest.getUsername())
                                .get();

                UserDetails userDetails = appUserDetailService.loadUserByUsername(
                                loginRequest.getUsername());

                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setUsername(user.getUsername());
                loginResponse.setEmail(user.getGuest().getEmail());
                loginResponse.setAuthorities(
                                userDetails
                                                .getAuthorities()
                                                .stream()
                                                .map(authority -> authority.getAuthority())
                                                .collect(Collectors.toList()));

                return loginResponse;
        }
}
