package br.com.zup.gerenciador.de.dailys.taskAtual;

import br.com.zup.gerenciador.de.dailys.taskAtual.TaskAtual;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskAtualRepository extends CrudRepository<TaskAtual, Long> {
    Optional<TaskAtual> findById (Long id);
}
