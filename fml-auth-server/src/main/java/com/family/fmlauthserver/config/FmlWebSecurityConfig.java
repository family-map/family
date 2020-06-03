package com.family.fmlauthserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class FmlWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(PasswordEncoderBean());
        super.configure(auth);
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        ArrayList<UserDetails> userDetails = new ArrayList<>();
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ADMIN";
            }
        });
        PasswordEncoder passwordEncode = PasswordEncoderBean();
        User user =new User(passwordEncode.encode("user_1"), passwordEncode.encode("123"), authorities);
        userDetails.add(user);
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(userDetails);
        return manager;
    }
    public PasswordEncoder PasswordEncoderBean() {
        return new BCryptPasswordEncoder();
    }
}
