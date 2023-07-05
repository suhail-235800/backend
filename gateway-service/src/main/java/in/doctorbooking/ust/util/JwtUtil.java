package in.doctorbooking.ust.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public static final String SECRET = "EE1zVw4ROsAeR4ltYkhEj+Mld8QHvOGskAu9RjI9VLPX7s4gGFrebPzoRnj0Ll3G";

    public void validateToken(String token) {
//        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token);
//            logger.warn("claimsJws: " + claimsJws);
//            Date expirationDate = claimsJws.getBody().getExpiration();
//            Date currentDate = new Date();
//            return !expirationDate.before(currentDate);
//        } catch (Exception e) {
//            return false;
//        }
    }
//    public void validateToken(final String token) {
//        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
//    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
