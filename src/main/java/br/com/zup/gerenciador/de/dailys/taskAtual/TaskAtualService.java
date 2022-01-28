package br.com.zup.gerenciador.de.dailys.taskAtual;

import br.com.zup.gerenciador.de.dailys.taskAtual.dtos.TaskAtualEntradaDTO;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskAtualService {
    @Autowired
    private TaskAtualRepository taskAtualRepository;
    @Autowired
    private UsuarioService usuarioService;

    public TaskAtual salvarTaskAtual(String emailUsuario){
        Usuario usuario = usuarioService.exibirUsuarioPorEmail(emailUsuario);
        TaskAtual taskAtual = new TaskAtual();
        taskAtual.setUsuario(usuario);
        return taskAtualRepository.save(taskAtual);
    }

    public Iterable<TaskAtual>exibirTasksAtuais(){
        return taskAtualRepository.findAll();
    }

    public TaskAtual atualizarTaskAtual( Long id, TaskAtualEntradaDTO taskAtualDTO){
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
