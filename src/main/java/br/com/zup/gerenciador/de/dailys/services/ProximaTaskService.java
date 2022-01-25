package br.com.zup.gerenciador.de.dailys.services;

import br.com.zup.gerenciador.de.dailys.model.ProximaTask;
import br.com.zup.gerenciador.de.dailys.repositories.ProximaTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProximaTaskService {
    @Autowired
    private ProximaTaskRepository proximaTaskRepository;

    public ProximaTask salvarProximaTask(ProximaTask proximaTask) {
        return proximaTaskRepository.save(proximaTask);
    }

    public Iterable<ProximaTaskRepository> exibirProximasTasks() {
        return proximaTaskRepository.findAll();
    }

    public void deletarProximaTask(Long id) {
        proximaTaskRepository.deleteById(id);
    }
}
