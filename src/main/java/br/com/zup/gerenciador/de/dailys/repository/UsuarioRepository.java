package br.com.zup.gerenciador.de.dailys.repository;

import br.com.zup.gerenciador.de.dailys.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
}
