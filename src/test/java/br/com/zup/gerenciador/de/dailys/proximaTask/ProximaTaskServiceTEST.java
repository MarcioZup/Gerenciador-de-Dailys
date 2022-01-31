package br.com.zup.gerenciador.de.dailys.proximaTask;


import br.com.zup.gerenciador.de.dailys.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
public class ProximaTaskServiceTEST {
    @MockBean
    private ProximaTaskRepository proximaTaskRepository;
    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProximaTaskService proximaTaskService;

}
