package br.com.zup.gerenciador.de.dailys.taskAtual;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskAtualRepository extends CrudRepository<TaskAtual, Long> {
    Optional<TaskAtual> findById (Long id);
    //boolean existsByUsuarioEmail(String email, String s);

}
