package br.com.zup.gerenciador.de.dailys.proximaTask;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;

@Repository
public interface ProximaTaskRepository extends CrudRepository<ProximaTask, Long> {
    Optional<ProximaTask> findById (Long id);
}
