package br.com.zup.gerenciador.de.dailys.taskAtual;

import br.com.zup.gerenciador.de.dailys.taskAtual.TaskAtual;
import br.com.zup.gerenciador.de.dailys.taskAtual.TaskAtualRepository;
import br.com.zup.gerenciador.de.dailys.taskAtual.dtos.TaskAtualDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public TaskAtual atualizarTaskAtual( Long id, TaskAtualDTO taskAtualDTO){
        Optional<TaskAtual> taskAtualOptional = taskAtualRepository.findById(id);
        if (taskAtualOptional.isEmpty()){
            throw  new TaskAtualInexistente("Não existe task atual vinculado a esse ID");
        }

        TaskAtual taskAtualParaAtualizar = taskAtualOptional.get();
        taskAtualParaAtualizar.setDescricao(taskAtualDTO.getDescricao());

        taskAtualRepository.save(taskAtualParaAtualizar);

        return taskAtualParaAtualizar;
    }

    public void deletartaskAtual(Long id ){
        if(!taskAtualRepository.existsById(id)){
            throw  new TaskAtualInexistente("Task atual inexistente");
        }
        taskAtualRepository.deleteById(id);
    }

}
