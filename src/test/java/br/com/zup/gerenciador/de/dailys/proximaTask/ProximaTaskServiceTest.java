package br.com.zup.gerenciador.de.dailys.proximaTask;

import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskEntradaDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskSaidaDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.exception.ProximaTaskInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class ProximaTaskServiceTest {

    @MockBean
    private ProximaTaskRepository proximaTaskRepository;
    @MockBean
    private UsuarioRepository usuarioRepository;
    @Mock
    private ProximaTaskEntradaDTO proximaTaskEntradaDTO;

    @Autowired
    private ProximaTaskService proximaTaskService;

    private ProximaTask proximaTask;

    private Usuario usuario;

    private ProximaTaskSaidaDTO proximaTaskSaidaDTO;


    @BeforeEach
    private void setup(){

        usuario = new Usuario();
        usuario.setEmail("let.let@zup.com.br");
        usuario.setSenha("Xablau123");
        usuario.setNome("Let Marçal Matias");
        usuario.setNomeDaSquad("Vivo");

        proximaTask = new ProximaTask();
        proximaTask.setId(Long.valueOf(1));
        proximaTask.setDescricao("Anything");
        proximaTask.setDataInicio(LocalDate.ofEpochDay(20-01-2022));
        proximaTask.setPrevisaoFim("01-02-2022");
        proximaTask.setUsuario(usuario);
    }



    @Test
    public void testeSalvarProximaTaskNegativo() {
        Mockito.when(usuarioRepository.existsById(Mockito.anyString())).thenReturn(false);

        Assertions.assertThrows(UsuarioInexistente.class,
                () -> {proximaTaskService.salvarProximaTask(proximaTaskEntradaDTO);});
    }

    @Test
    public void testeSalvarProximaTaskPositivo(){
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
        Mockito.when(usuarioRepository.existsById(Mockito.anyString())).thenReturn(true);
        Mockito.when(proximaTaskRepository.save(Mockito.any(ProximaTask.class))).thenReturn(proximaTask);

        proximaTaskRepository.save(proximaTask);

        Mockito.verify(proximaTaskRepository, Mockito.times(1)).save(proximaTask);
    }

    @Test
    public void testeVisualizarProximasTasksCadastradasPositivo(){
        List<ProximaTask> proximaTasks = Arrays.asList(proximaTask);
        Mockito.when(proximaTaskRepository.findAll()).thenReturn(proximaTasks);
        List<ProximaTask> proximaTasksResposta = (List<ProximaTask>) proximaTaskService.exibirProximasTasks();
        Mockito.verify(proximaTaskRepository, Mockito.times(1)).findAll();
    }


    @Test
    public void testeAtualizarProximaTaskPositivo(){

        Mockito.when(proximaTaskRepository.save(proximaTask)).thenReturn(proximaTask);
        Mockito.when(proximaTaskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(proximaTask));

        final ProximaTaskSaidaDTO proximaTaskSaidaDTOMock = mock(ProximaTaskSaidaDTO.class);

        proximaTaskService.atualizarProximaTask(Mockito.anyLong(), proximaTaskSaidaDTOMock);
        Mockito.verify(proximaTaskRepository, Mockito.times(1)).save(proximaTask);
    }

    @Test
    public void testeAtualizarProximaTaskNegativo(){
        Mockito.when(proximaTaskRepository.save(Mockito.any())).thenReturn(proximaTask);
        Mockito.when(proximaTaskRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        final ProximaTaskSaidaDTO proximaTaskSaidaDTOMOCK = mock(ProximaTaskSaidaDTO.class);

        ProximaTaskInexistente exception = Assertions.assertThrows(ProximaTaskInexistente.class,
                () -> {proximaTaskService.atualizarProximaTask(Long.valueOf(0), proximaTaskSaidaDTOMOCK);});

        Assertions.assertEquals("Não existe próxima task vinculado a este ID", exception.getMessage());
    }

    @Test
    public void testeDeletarProximaTaskPositivo(){
        Mockito.when(proximaTaskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(proximaTask));
        Mockito.doNothing().when(proximaTaskRepository).deleteById(Mockito.anyLong());
        proximaTaskService.deletarProximaTask(proximaTask.getId());

        Mockito.verify(proximaTaskRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    public void testeDeletarProximaTaskNegativo(){
        Mockito.doNothing().when(proximaTaskRepository).deleteById(Mockito.anyLong());

        ProximaTaskInexistente exception = Assertions.assertThrows(ProximaTaskInexistente.class,
                () -> {proximaTaskService.deletarProximaTask(Long.valueOf(0));});

        Assertions.assertEquals("Não existe próxima task vinculado a este ID", exception.getMessage());
    }

}


