package com.micr.userver.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Bean
    public SecurityFilterChain secFilterChain(HttpSecurity httpsecurity) throws Exception {
        httpsecurity.csrf(customizer -> customizer.disable());
        httpsecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        httpsecurity.formLogin(Customizer.withDefaults());
        httpsecurity.httpBasic(Customizer.withDefaults());
        return httpsecurity.build();
    }

}
