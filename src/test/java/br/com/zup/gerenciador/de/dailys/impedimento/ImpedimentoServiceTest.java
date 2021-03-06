package br.com.zup.gerenciador.de.dailys.impedimento;

import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoEntradaDTO;
import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoSaidaDTO;
import br.com.zup.gerenciador.de.dailys.impedimento.exception.ImpedimentoInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
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
        private ImpedimentoEntradaDTO impedimentoEntradaDTO;

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
        public void testeDeletarImpedimentoNegativo(){
            Mockito.doNothing().when(impedimentoRepository).deleteById(Mockito.anyLong());

            ImpedimentoInexistente exception = Assertions.assertThrows(ImpedimentoInexistente.class,
                    () -> {impedimentoService.deletarImpedimento(Long.valueOf(0));});

            Assertions.assertEquals("Impedimento n??o encontrado", exception.getMessage());
        }


        @Test
        public void testeAtualizarImpedimentoPositivo(){

            Mockito.when(impedimentoRepository.save(impedimento)).thenReturn(impedimento);
            Mockito.when(impedimentoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(impedimento));
            final ImpedimentoSaidaDTO impedimentoSaidaDTOMock = mock(ImpedimentoSaidaDTO.class);
            impedimentoService.alterarDescricaoImpedimento(Mockito.anyLong(), impedimentoSaidaDTOMock);
            Mockito.verify(impedimentoRepository, Mockito.times(1)).save(impedimento);
        }

        @Test
        public void testeAtualizarImpedimentoNegativo(){
            Mockito.when(impedimentoRepository.save(Mockito.any())).thenReturn(impedimento);
            Mockito.when(impedimentoRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

            final ImpedimentoSaidaDTO impedimentoSaidaDTOMOCK = mock(ImpedimentoSaidaDTO.class);

            ImpedimentoInexistente exception = Assertions.assertThrows(ImpedimentoInexistente.class,
                    () -> {impedimentoService.alterarDescricaoImpedimento(Long.valueOf(0), impedimentoSaidaDTO);});

            Assertions.assertEquals("N??o existe impedimento vinculado a este ID", exception.getMessage());
        }

        @Test
        public void testeVisualizarImpedimentosCadastrados(){
            List<Impedimento> impedimentos = Arrays.asList(impedimento);
            Mockito.when(impedimentoRepository.findAll()).thenReturn(impedimentos);
            List<Impedimento> proximaTasksResposta = (List<Impedimento>) impedimentoService.exibirImpedimentos();
            Mockito.verify(impedimentoRepository, Mockito.times(1)).findAll();
        }

}
