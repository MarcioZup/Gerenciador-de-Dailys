package br.com.zup.gerenciador.de.dailys.impedimento;

import br.com.zup.gerenciador.de.dailys.impedimento.Impedimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImpedimentoRepository extends CrudRepository<Impedimento, Long> {
    Optional<Impedimento> findById(Long id);

}
