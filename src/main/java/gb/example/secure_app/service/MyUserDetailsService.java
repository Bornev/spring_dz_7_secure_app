package gb.example.secure_app.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("admin".equals(username)) {
//            return new User("admin", "$2a$10$7.qOHj12T7Jm.ZFoMZVJBOjY3mJ2z7MLH/U7JZn.DH5VTCkO1QoKu", new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found");
//        }

        if ("admin".equals(username)) {
            return new User("admin", "$2a$10$2keC1Ca7Zx7hcAx6RTtXiO.dRb8zadA0uGjgk95RM0Mzj5bZ/7Wtu", new ArrayList<>());
        } else if ("user".equals(username)) {
            return new User("user", "$2a$10$WWjsijCoT5aoFFU.p4J3teSoH4Ay.0Fvek5vQWirjpm6OdRIpS7su", new ArrayList<>()); // замените на хэш нового пароля
        } else {
            throw new UsernameNotFoundException("User not found");
        }

    }
}

