package br.com.zup.gerenciador.de.dailys.taskAtual;

import br.com.zup.gerenciador.de.dailys.taskAtual.TaskAtual;
import br.com.zup.gerenciador.de.dailys.taskAtual.TaskAtualRepository;
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
        if(!taskAtualRepository.existsById(id)){
            throw  new TaskAtualInexistente("Task atual inexistente");
        }
        taskAtualRepository.deleteById(id);
    }

}
