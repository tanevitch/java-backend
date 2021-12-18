package ttps.spring.services;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;


@Service
public class TokenService {
	final static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String generarToken(String email, int segundos) {
		Date exp = new Date(System.currentTimeMillis() + segundos * 1000);
		System.out.println(exp);
		return Jwts.builder().setSubject(email).signWith(key).setExpiration(exp).compact();
	}
	
	public static boolean validar(String token) {
		String prefix =  "Bearer";
		try {
			if (token.startsWith(prefix)) {
				token = token.substring(prefix.length()).trim();
			}
			Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token).getBody();

            System.out.println("ID: " + claims.getId());
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Issuer: " + claims.getIssuer());
            System.out.println("Expiration: " + claims.getExpiration());
            return true;
		}
		catch(ExpiredJwtException exp) {
			return false;
		}
		catch(JwtException e) {
			System.out.println(e);
			return false;
		}
	}
	
}
