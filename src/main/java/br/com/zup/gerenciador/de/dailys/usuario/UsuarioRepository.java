package br.com.zup.gerenciador.de.dailys.usuario;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String email);

    Integer countByEmail(String email);

    List<Usuario> findByNomeDaSquad (String nomeDaSquad);

}
