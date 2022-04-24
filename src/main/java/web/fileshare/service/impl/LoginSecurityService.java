package web.fileshare.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    public UserDetails loadUserByUsername(String username){
        try {
           var user = ld.loginSecurity(username);
           if (user != null) {
               PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
               List<GrantedAuthority> authorities = new ArrayList<>();//사용자의 권한정보를 설정하는 부분.
               authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
               return new CustomUserDetails(user.getId(),passwordEncoder.encode(user.getPassword())
                       ,true, true,true,true, authorities);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
