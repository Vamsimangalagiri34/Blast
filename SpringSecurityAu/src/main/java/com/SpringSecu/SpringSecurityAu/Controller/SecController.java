package com.SpringSecu.SpringSecurityAu.Controller;

import com.SpringSecu.SpringSecurityAu.Model.User;
import com.SpringSecu.SpringSecurityAu.Service.JWTService;
import com.SpringSecu.SpringSecurityAu.Service.SecService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SecController {

    @Autowired
    SecService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

     @PostMapping("/register")
     public String register(@RequestBody User user){
         service.addUser(user);
         return "from register";
     }

     @PostMapping("/login")
     public String login(@RequestBody User user) {
         System.out.println("from login"+ user);
         Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
         if(authentication.isAuthenticated()){
             System.out.println("authenticate");
             return jwtService.generateToken(user.getUsername());
         }
         else{
             System.out.println("authenticate");
             return "fail";
         }
     }




    @GetMapping("/")
    public String greet(HttpServletRequest http){
        return "Hello , Vamsi"+http.getSession().getId()+" "+ (CsrfToken) http.getAttribute("_csrf");
    }

    @GetMapping("csrf-token")
    public CsrfToken getCsrf(HttpServletRequest http){
        return (CsrfToken) http.getAttribute("_csrf");
    }

    @PostMapping("/add")
   public void addUser(@RequestBody User user){
        service.addUser(user);
    }

    @GetMapping("/all")
    public List<User> getAll(){
       return service.getUsersL();
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user){
        service.updateUser(user);
    }

    @GetMapping("/user/{str}")
    public User getUserByCont(@PathVariable String str){
         return  service.getUsers(str);
    }

    @GetMapping("/free/")
    public ResponseEntity<?> free(){
         return  ResponseEntity.ok("free");
    }
//    @PostMapping()


}
