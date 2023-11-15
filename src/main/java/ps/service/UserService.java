package ps.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ps.model.Usuario;
import dto.UsuarioDTO;

public interface UserService extends UserDetailsService {
	Usuario save(UsuarioDTO usuario_dto);
}


