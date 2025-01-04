package com.micr.userver.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.micr.userver.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain secFilterChain(HttpSecurity httpsecurity) throws Exception {
        httpsecurity.csrf(AbstractHttpConfigurer::disable);
        httpsecurity.authorizeHttpRequests(request -> request
                                                    .requestMatchers("login","createUser")
                                                    .permitAll()
                                                    .anyRequest().authenticated()
        );
        httpsecurity.formLogin(Customizer.withDefaults());
        httpsecurity.httpBasic(Customizer.withDefaults());
        return httpsecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(customUserDetailsService);
        return provider;
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user1 = User
    //     .withDefaultPasswordEncoder()
    //     .username("vignesh")
    //     .password("password")
    //     .roles("USER")
    //     .build();
    //     return new InMemoryUserDetailsManager(user1);
    // }

}
