package com.example.authtest.security;

import com.example.authtest.config.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityUserDetails implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Guidos test" + username);
        return CustomUserDetails.build();
    }

    /*@Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        System.out.println("Guidos test" + user);
        System.out.println("Guidos test" + newPassword);
        return this.customUserDetails;
    }*/
}
