package br.com.zup.gerenciador.de.dailys.repository;

import br.com.zup.gerenciador.de.dailys.model.Impedimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpedimentoRepository extends CrudRepository<Impedimento, Long> {
}
