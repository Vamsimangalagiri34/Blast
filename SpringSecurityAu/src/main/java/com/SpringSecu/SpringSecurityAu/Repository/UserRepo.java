package com.SpringSecu.SpringSecurityAu.Repository;
import com.SpringSecu.SpringSecurityAu.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserRepo extends JpaRepository<User,Integer> {
//    User findByName(String name);

    @Query(value = "SELECT * FROM user WHERE username LIKE %?1%", nativeQuery = true)
    User searchByUsername(String username);

    User findByUsername(String username);

//    User findBySkillsetContaing("")

//    User user =new User();
}
