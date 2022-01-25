package br.com.zup.gerenciador.de.dailys.taskAtual;

import br.com.zup.gerenciador.de.dailys.taskAtual.TaskAtual;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAtualRepository extends CrudRepository<TaskAtual, Long> {
}
