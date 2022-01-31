package br.com.zup.gerenciador.de.dailys.proximaTask;

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
import java.time.LocalDate;

@SpringBootTest
public class ProximaTaskServiceTEST {
    @MockBean
    private ProximaTaskRepository proximaTaskRepository;
    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProximaTaskService proximaTaskService;

    private ProximaTask proximaTask;

    private Usuario usuario;

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
                () -> {proximaTaskService.salvarProximaTask(usuario.getEmail());});
    }

    @Test
    public void testeSalvarProximaTaskPositivo(){
        Mockito.when(usuarioRepository.existsById(Mockito.anyString())).thenReturn(true);
        Mockito.when(proximaTaskRepository.save(Mockito.any(ProximaTask.class))).thenReturn(proximaTask);

        proximaTaskService.salvarProximaTask(usuario.getEmail());

        Mockito.verify(proximaTaskRepository, Mockito.times(1)).save(proximaTask);
    }

}


