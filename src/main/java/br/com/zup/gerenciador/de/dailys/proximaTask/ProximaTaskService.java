package br.com.zup.gerenciador.de.dailys.proximaTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProximaTaskService {
    @Autowired
    private ProximaTaskRepository proximaTaskRepository;

    public ProximaTask salvarProximaTask(ProximaTask proximaTask) {
        return proximaTaskRepository.save(proximaTask);
    }

    public Iterable<ProximaTask> exibirProximasTasks() {
        return proximaTaskRepository.findAll();
    }
    public ProximaTask atualizarProximaTask(ProximaTask proximaTask){
        return proximaTaskRepository.save(proximaTask);
    }

    public void deletarProximaTask(Long id) {
        proximaTaskRepository.deleteById(id);
    }

}
