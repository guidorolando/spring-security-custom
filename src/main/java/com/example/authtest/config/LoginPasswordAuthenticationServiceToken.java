package com.example.authtest.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public class LoginPasswordAuthenticationServiceToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;

    //private final String company;

    public LoginPasswordAuthenticationServiceToken(Object principal,
                                     Object credentials) {

        super(principal, credentials);
        //this.company = company;
    }

    public LoginPasswordAuthenticationServiceToken(Object principal,
                                     Object credentials,
                                     GrantedAuthority[] authorities) {

        super(principal, credentials, Arrays.asList(authorities));
        //this.company = company;
    }

    public LoginPasswordAuthenticationServiceToken(Object principal,
                                     Object credentials,
                                     Collection<? extends GrantedAuthority> authorities) {

        super(principal, credentials, authorities);
        //this.company = company;
    }

}
