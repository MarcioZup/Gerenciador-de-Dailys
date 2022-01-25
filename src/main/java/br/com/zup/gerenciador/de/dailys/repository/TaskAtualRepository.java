package br.com.zup.gerenciador.de.dailys.repository;

import br.com.zup.gerenciador.de.dailys.model.TaskAtual;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAtualRepository extends CrudRepository<TaskAtual, Long> {
}
