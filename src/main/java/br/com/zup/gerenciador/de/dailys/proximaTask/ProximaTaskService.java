package br.com.zup.gerenciador.de.dailys.proximaTask;

import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskSaidaDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.exception.ProximaTaskInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProximaTaskService {
    @Autowired
    private ProximaTaskRepository proximaTaskRepository;
    @Autowired
    private UsuarioService usuarioService;

    public ProximaTask salvarProximaTask(String emailUsuario) {
        Usuario usuario = usuarioService.exibirUsuarioPorEmail(emailUsuario);
        ProximaTask proximaTask = new ProximaTask();
        proximaTask.setUsuario(usuario);

        return proximaTaskRepository.save(proximaTask);

    }

    public Iterable<ProximaTask> exibirProximasTasks() {

        return proximaTaskRepository.findAll();

    }
    public ProximaTask atualizarProximaTask(Long id, ProximaTaskSaidaDTO proximaTaskDTO){

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
