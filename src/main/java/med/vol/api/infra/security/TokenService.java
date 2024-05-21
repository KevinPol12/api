package med.vol.api.infra.security;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import med.vol.api.domain.usuario.Usuario;

@Service
public class TokenService {

	@Value("${api.security.secret}")
	private String apiSecret;
	

	public String generarToken(Usuario usuario) {
		
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    
		    return JWT.create()
		        .withIssuer("voll med")
		        .withSubject(usuario.getLogin())
		        .withClaim("id",usuario.getId())
		        .withExpiresAt(generarFechaExpiracion())
		        .sign(algorithm);
		    
		} catch (JWTCreationException exception){
			throw new RuntimeException();
		}
		
		
	}
	
	public String verifyToken(String inputToken) {
		
		DecodedJWT verifier = null;
		try {
			
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    verifier = JWT.require(algorithm)
		        .withIssuer("voll med")
		        .build()
		        .verify(inputToken);
		    verifier.getSubject();
		} catch (JWTVerificationException e){
			throw new RuntimeException("Token es invalido");
		}
		
		if(verifier.getSubject()==null) {
			throw new RuntimeException("Verifier Invalido");
		}
		return verifier.getSubject();
 
	}
	
	
	

	private Instant generarFechaExpiracion() {

		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
	}
	
}
