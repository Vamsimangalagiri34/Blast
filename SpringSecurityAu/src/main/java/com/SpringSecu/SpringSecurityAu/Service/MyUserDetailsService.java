package com.SpringSecu.SpringSecurityAu.Service;
//import com.SpringSecu.SpringSecurityAu.Model.AppUser;
import com.SpringSecu.SpringSecurityAu.Repository.UserRepo;
//import com.SpringSecu.SpringSecurityAu.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.SpringSecu.SpringSecurityAu.Model.User user = userRepository.findByUsername(username);
        System.out.println(user.getUsername()+" "+user.getPassword());
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());

    }
}
