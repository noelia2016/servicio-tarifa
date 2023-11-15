package ps.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ps.model.Usuario;
import dto.UsuarioDTO;
import ps.repository.UsuarioRepository;

@Service
public class DefaultUserServiceImpl implements UserService {

	@Autowired
	UsuarioRepository usuario_repository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuario_repository.findByEmail(username);

		return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(),
				mapRolesToAuthorities(usuario.getRol()));
	}

	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(String rol) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(rol));
		return authorities;
	}

	@Override
	public Usuario save(UsuarioDTO usuario_dto) {
		String role = "ROLE_USER";
		if (usuario_dto.getRole().equals("ADMIN"))
			role = "ROLE_ADMIN";

		Usuario usuario = new Usuario();
		usuario.setEmail(usuario_dto.getEmail());
		usuario.setPassword(passwordEncoder.encode(usuario_dto.getPassword()));
		usuario.setRol(role);

		return this.usuario_repository.save(usuario);
	}
}


