package ps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ps.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /* devuelve el email para poder conectarse */
	Usuario findByEmail(String email);
}

