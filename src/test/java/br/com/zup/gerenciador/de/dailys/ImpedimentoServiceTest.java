package br.com.zup.gerenciador.de.dailys;

import br.com.zup.gerenciador.de.dailys.impedimento.Impedimento;
import br.com.zup.gerenciador.de.dailys.impedimento.ImpedimentoRepository;
import br.com.zup.gerenciador.de.dailys.impedimento.ImpedimentoService;
import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoSaidaDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskSaidaDTO;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioRepository;
import org.hibernate.tool.hbm2ddl.ImportScriptException;
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
    public class ImpedimentoServiceTest {
        @MockBean
        private ImpedimentoRepository impedimentoRepository;
        @MockBean
        private UsuarioRepository usuarioRepository;

        @Autowired
        private ImpedimentoService impedimentoService;

        private Impedimento impedimento;

        private Usuario usuario;

        private ImpedimentoSaidaDTO impedimentoSaidaDTO;

        @BeforeEach
        private void setup(){

            usuario = new Usuario();
            usuario.setEmail("karen.almeida@zup.com.br");
            usuario.setSenha("Xablau123");
            usuario.setNome("karen");
            usuario.setNomeDaSquad("Vivo");

            impedimento = new Impedimento();
            impedimento.setId(Long.valueOf(1));
            impedimento.setDescricao("Anything");
            impedimento.setUsuario(usuario);
        }

        @Test
        public void testeDeletarImpedimentoPositivo(){
            Mockito.when(impedimentoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(impedimento));
            Mockito.doNothing().when(impedimentoRepository).deleteById(Mockito.anyLong());
            // impedimentoService.deletarImpedimento(impedimento.getId());
            impedimentoRepository.deleteById(impedimento.getId());


            Mockito.verify(impedimentoRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
        }

        @Test
        public void testeSalvarImpedimentoNegativo() {
            Mockito.when(usuarioRepository.existsById(Mockito.anyString())).thenReturn(false);

            Assertions.assertThrows(UsuarioInexistente.class,
                    () -> {impedimentoService.salvarImpedimento(usuario.getEmail());});
        }

        @Test
        public void testeSalvarImpedimentoPositivo() {
            Mockito.when(usuarioRepository.existsById(Mockito.anyString())).thenReturn(true);

            Assertions.assertThrows(UsuarioInexistente.class,
                    () -> {impedimentoService.salvarImpedimento(usuario.getEmail());});
        }


        @Test
        public void testeAtualizarProximoImpedimento(){

            Mockito.when(impedimentoRepository.save(impedimento)).thenReturn(impedimento);
            Mockito.when(impedimentoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(impedimento));
            final ImpedimentoSaidaDTO impedimentoSaidaDTOMock = mock(ImpedimentoSaidaDTO.class);
            impedimentoService.alterarDescricaoImpedimento(Mockito.anyLong(), impedimentoSaidaDTOMock);
            Mockito.verify(impedimentoRepository, Mockito.times(1)).save(impedimento);
        }

        @Test
        public void testeVisualizarImpedimentosCadastrados(){
            List<Impedimento> impedimentos = Arrays.asList(impedimento);
            Mockito.when(impedimentoRepository.findAll()).thenReturn(impedimentos);
            List<Impedimento> proximaTasksResposta = (List<Impedimento>) impedimentoService.exibirImpedimentos();
            Mockito.verify(impedimentoRepository, Mockito.times(1)).findAll();
        }
}
