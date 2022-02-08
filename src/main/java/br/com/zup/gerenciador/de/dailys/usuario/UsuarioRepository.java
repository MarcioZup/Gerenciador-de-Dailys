package br.com.zup.gerenciador.de.dailys.usuario;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByNomeDaSquad(String nomeDaSquad);
    Integer countByEmail(String email);


}
