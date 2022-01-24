package br.com.zup.gerenciador.de.dailys.repositories;

import br.com.zup.gerenciador.de.dailys.model.Atividade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends CrudRepository<Atividade, Long> {

}
