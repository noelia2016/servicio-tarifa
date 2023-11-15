package ps.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ps.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWT_Utilidades {

	@Autowired
	UserService userService;

    // la pasa secreta para generar tokens
	private final String SECRET = "skfwiegwbfbfshfuwegbfskfbwietwet";

    /* */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}


	public Claims extractUserRole(String token) {
		return extractAllClaims(token);
	}

    /* si poseee fecha de expiracion el token */
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

    /* genera el token a compatir para el usuario que se autentifico */
	public String generateToken(Authentication authentication) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, authentication);
	}

    /* crea el token en si para devolverlo */
	private String createToken(Map<String, Object> claims, Authentication authentication) {
		String role = authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet())
				.iterator().next();
		return Jwts.builder()
				.claim("role", role)
				.setSubject(authentication.getName())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)))
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();
	}

    /**
     * @param token
     * @param userDetails
     * Verifica que el token sea valido para el usuario 
     * para ello no tiene que a ver expirado el mismo y el usario tiene que ser el mismo
     */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

    /**
     * 
     * @param token
     * @param existingAuth
     * @param userDetails
     * @return
     * 
     * Verifica el usuario y password del usuario que peticiono
     */
	public UsernamePasswordAuthenticationToken getAuthenticationToken(final String token,
			final Authentication existingAuth, final UserDetails userDetails) {

		Claims claims = extractAllClaims(token);

		final Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get("role").toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

}


