package web.fileshare.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.fileshare.domain.repository.LoginDAO;
import web.fileshare.security.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LoginSecurityService implements UserDetailsService {

    @Autowired
    private LoginDAO ld;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
           var user = ld.loginSecurity(username);
           if (user != null) {
               PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
               CustomUserDetails customUserDetails = new CustomUserDetails();
               customUserDetails.setUsername(user.getId());
               customUserDetails.setPassword(passwordEncoder.encode(user.getPassword()));

               List<GrantedAuthority> authorities = new ArrayList<>();//사용자의 권한정보를 설정하는 부분.
               authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));
               customUserDetails.setAuthorities(authorities);

               customUserDetails.setEnabled(true);
               customUserDetails.setAccountNonExpired(true);
               customUserDetails.setAccountNonLocked(true);
               customUserDetails.setCredentialsNonExpired(true);
               log.info(customUserDetails.toString());
               return customUserDetails;
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
