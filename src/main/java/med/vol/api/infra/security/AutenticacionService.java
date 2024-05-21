package med.vol.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import med.vol.api.domain.usuario.UsuarioRepository;

@Service
public class AutenticacionService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
				System.out.println("Authservice 1, username is: "+
				username);
		
				UserDetails userDetails = usuarioRepository.findByLogin(username);
		
				System.out.println("AuthService 2 userDetails content: "+
									userDetails.getUsername()+ userDetails.getPassword());
				
				return userDetails;
	}
	
	
	
}