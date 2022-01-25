package br.com.zup.gerenciador.de.dailys.service;

import br.com.zup.gerenciador.de.dailys.model.TaskAtual;
import br.com.zup.gerenciador.de.dailys.repository.TaskAtualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskAtualService {
    @Autowired
    private TaskAtualRepository taskAtualRepository;

    public TaskAtual salvarTaskAtual(TaskAtual taskAtual){
        return taskAtualRepository.save(taskAtual);
    }

    public Iterable<TaskAtual>exibirTasksAtuais(){
        return taskAtualRepository.findAll();
    }

    public TaskAtual atualizarTaskAtual(TaskAtual taskAtual){
        return  taskAtualRepository.save(taskAtual);
    }

    public void deletartaskAtual(Long id ){
        taskAtualRepository.deleteById(id);
    }

}
