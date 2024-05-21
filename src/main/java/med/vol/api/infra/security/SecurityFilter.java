package med.vol.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.vol.api.domain.usuario.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepositoy;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		
			var inputToken = request.getHeader("Authorization");
			
			if(inputToken!=null) {
				inputToken = inputToken.replace("Bearer ","");
						var subject = tokenService.verifyToken(inputToken);
				if(subject != null) {
					//Token es valido
					var usuario = usuarioRepositoy.findByLogin(subject);
					var authentication = new UsernamePasswordAuthenticationToken(usuario,
							null,usuario.getAuthorities());
					SecurityContextHolder.getContext()
					.setAuthentication(authentication);
					
				}
				
			}			
			filterChain.doFilter(request, response);
			
		
	}	
	
}
