package gb.example.secure_app.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "newpasswordpassword"; // замените на нужный пароль
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}