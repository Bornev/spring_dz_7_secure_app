package gb.example.secure_app.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {

    // Получение секретного ключа из файла настроек
    @Value("${jwt.secret.key}")
    private String secretKey;

    /**
     * Генерация JWT токена для переданного пользователя.
     * @param userDetails информация о пользователе
     * @return строка JWT токена
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Создание JWT токена с указанными утверждениями (claims) и субъектом (subject).
     * @param claims утверждения
     * @param subject субъект токена (обычно имя пользователя)
     * @return строка JWT токена
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims) // Утверждения
                .setSubject(subject) // Субъект
                .setIssuedAt(new Date(System.currentTimeMillis())) // Дата создания токена
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Срок действия 10 часов
                .signWith(getSecureKey(), SignatureAlgorithm.HS256) // Подпись токена
                .compact();
    }

    /**
     * Получение объекта безопасного ключа для подписи.
     * @return объект SecretKey
     */
    private SecretKey getSecureKey() {
        // Если ключ недостаточно длинный, создаётся безопасный ключ программно
        if (secretKey.length() < 32) {
            System.out.println("Provided key is too short. Generating a secure key...");
            return Keys.secretKeyFor(SignatureAlgorithm.HS256);

        }
        return Keys.hmacShaKeyFor(secretKey.getBytes());

    }

}