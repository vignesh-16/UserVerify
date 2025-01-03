package com.micr.userver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.micr.userver.repository.UsersCollection;
import com.micr.userver.model.UserDO;
import com.micr.userver.model.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersCollection userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Users user = repo.findByUsername(username);
        UserDO user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with provided username!");
        }
        return new UserPrincipal(user);
    }

}
