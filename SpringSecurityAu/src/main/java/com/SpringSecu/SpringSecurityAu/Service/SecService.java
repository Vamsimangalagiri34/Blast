package com.SpringSecu.SpringSecurityAu.Service;

import com.SpringSecu.SpringSecurityAu.Model.User;
import com.SpringSecu.SpringSecurityAu.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SecService {

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12); // ✅ Injected instead of manually creating

    public void addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword())); // ✅ Encode before saving
        repo.save(user);
    }

    public List<User> getUsersL() {
        return repo.findAll();
    }

    public User updateUser(User user) {
       user.setPassword(encoder.encode(user.getPassword()));
       System.out.println(user.getPassword());
       return repo.save(user);
    }

    public  User getUsers(String str){
        return repo.searchByUsername(str);
    }


}
