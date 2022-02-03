package br.com.zup.gerenciador.de.dailys.taskAtual;

import br.com.zup.gerenciador.de.dailys.taskAtual.TaskAtual;
import br.com.zup.gerenciador.de.dailys.taskAtual.TaskAtualRepository;
import br.com.zup.gerenciador.de.dailys.taskAtual.TaskAtualService;
import br.com.zup.gerenciador.de.dailys.taskAtual.dtos.TaskAtualSaidaDTO;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class TaskAtualServiceTest {
    @MockBean
    private TaskAtualRepository taskAtualRepository;
    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TaskAtualService taskAtualService;

    private TaskAtual taskAtual;

    private Usuario usuario;

    private TaskAtualSaidaDTO taskAtualSaidaDTO;

    @BeforeEach
    private void setup(){

        usuario = new Usuario();
        usuario.setEmail("karen.almeida@zup.com.br");
        usuario.setSenha("Xablau123");
        usuario.setNome("karen");
        usuario.setNomeDaSquad("Vivo");

        taskAtual = new TaskAtual();
        taskAtual.setId(Long.valueOf(1));
        taskAtual.setDescricao("Everything");
        taskAtual.setUsuario(usuario);
    }

    @Test
    public void testeDeletarTaskAtualPositivo(){
        Mockito.when(taskAtualRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(taskAtual));
        Mockito.doNothing().when(taskAtualRepository).deleteById(Mockito.anyLong());
        // impedimentoService.deletarImpedimento(impedimento.getId());
        taskAtualRepository.deleteById(taskAtual.getId());


        Mockito.verify(taskAtualRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    public void testeSalvarImpedimentoNegativo() {
        Mockito.when(usuarioRepository.existsById(Mockito.anyString())).thenReturn(false);

        Assertions.assertThrows(UsuarioInexistente.class,
                () -> {taskAtualService.salvarTaskAtual(usuario.getEmail());});
    }

    @Test
    public void testeSalvarImpedimentoPositivo() {
        Mockito.when(usuarioRepository.existsById(Mockito.anyString())).thenReturn(true);

        Assertions.assertThrows(UsuarioInexistente.class,
                () -> {taskAtualService.salvarTaskAtual(usuario.getEmail());});
    }


    @Test
    public void testeAtualizarProximoImpedimento(){

        Mockito.when(taskAtualRepository.save(taskAtual)).thenReturn(taskAtual);
        Mockito.when(taskAtualRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(taskAtual));
        final TaskAtualSaidaDTO taskAtualSaidaDTOMock = mock(TaskAtualSaidaDTO.class);
        taskAtualService.atualizarTaskAtual(Mockito.anyLong(), taskAtualSaidaDTOMock);
        Mockito.verify(taskAtualRepository, Mockito.times(1)).save(taskAtual);
    }

    @Test
    public void testeVisualizarImpedimentosCadastrados(){
        List<TaskAtual> tasksAtuais = Arrays.asList(taskAtual);
        Mockito.when(taskAtualRepository.findAll()).thenReturn(tasksAtuais);
        List<TaskAtual> proximaTasksResposta = (List<TaskAtual>) taskAtualService.exibirTasksAtuais();
        Mockito.verify(taskAtualRepository, Mockito.times(1)).findAll();
    }
}