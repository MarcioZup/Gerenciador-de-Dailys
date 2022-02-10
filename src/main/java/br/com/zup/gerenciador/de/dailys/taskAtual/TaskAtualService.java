package br.com.zup.gerenciador.de.dailys.taskAtual;

import br.com.zup.gerenciador.de.dailys.taskAtual.dtos.TaskAtualEntradaDTO;
import br.com.zup.gerenciador.de.dailys.taskAtual.dtos.TaskAtualSaidaDTO;
import br.com.zup.gerenciador.de.dailys.taskAtual.exception.TaskAtualInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioService;
import br.com.zup.gerenciador.de.dailys.usuario.exceptions.UsuarioInexistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskAtualService {
    @Autowired
    private TaskAtualRepository taskAtualRepository;
    @Autowired
    private UsuarioService usuarioService;

    public TaskAtual salvarTaskAtual(TaskAtualEntradaDTO taskAtualEntradaDTO){
        Usuario usuario = usuarioService.exibirUsuarioPorEmail(taskAtualEntradaDTO.getEmailUsuario());
        if (taskAtualEntradaDTO.getEmailUsuario().equals(usuario.getEmail())) {
            TaskAtual taskAtual = new TaskAtual();
            taskAtual.setUsuario(usuario);
            taskAtual.setDescricao(taskAtualEntradaDTO.getDescricao());
            taskAtual.setPrevisaoFim(taskAtualEntradaDTO.getPrevisaoFim());

            return taskAtualRepository.save(taskAtual);

        }
        throw new UsuarioInexistente("Usuário não encontrado");
    }

    public List<TaskAtual> exibirTasksAtuais(){
        List<TaskAtual> taskAtuals = (List<TaskAtual>) taskAtualRepository.findAll();

        if (taskAtuals.isEmpty()){
            throw new TaskAtualInexistente("Não há tasks atuais cadastradas");
        }
        return taskAtuals;
    }

    public TaskAtual atualizarTaskAtual(Long id, TaskAtualSaidaDTO taskAtualDTO){
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
