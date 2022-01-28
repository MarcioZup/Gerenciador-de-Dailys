package br.com.zup.gerenciador.de.dailys.proximaTask;

import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.exception.ProximaTaskInexistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public ProximaTask atualizarProximaTask(Long id, ProximaTaskDTO proximaTaskDTO){

        Optional<ProximaTask> proximaTaskOptional = proximaTaskRepository.findById(id);
        if (proximaTaskOptional.isEmpty()){
            throw new ProximaTaskInexistente("Não existe próxima task vinculado a este ID");
        }
        ProximaTask proximaTaskParaAtualizar = proximaTaskOptional.get();
        proximaTaskParaAtualizar.setDescricao(proximaTaskDTO.getDescricao());

        proximaTaskRepository.save(proximaTaskParaAtualizar);

        return proximaTaskParaAtualizar;
    }

    public void deletarProximaTask(Long id) {

        proximaTaskRepository.deleteById(id);

    }

}
