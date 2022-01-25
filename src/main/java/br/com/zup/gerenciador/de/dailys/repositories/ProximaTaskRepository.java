package br.com.zup.gerenciador.de.dailys.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProximaTaskRepository extends CrudRepository<ProximaTaskRepository, Long> {
}
