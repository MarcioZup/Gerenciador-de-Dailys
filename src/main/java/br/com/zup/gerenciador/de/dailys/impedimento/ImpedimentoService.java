package br.com.zup.gerenciador.de.dailys.impedimento;

import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoEntradaDTO;
import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoSaidaDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.ProximaTask;
import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskEntradaDTO;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImpedimentoService {
    @Autowired
    private ImpedimentoRepository impedimentoRepository;
    @Autowired
    private UsuarioService usuarioService;

    public Impedimento salvarImpedimento(ImpedimentoEntradaDTO impedimentoEntradaDTO) {
        Usuario usuario = usuarioService.exibirUsuarioPorEmail(impedimentoEntradaDTO.getEmailUsuario());
       Impedimento impedimento = new Impedimento();
        impedimento.setUsuario(usuario);
        impedimento.setDescricao(impedimentoEntradaDTO.getDescricao());

        return impedimentoRepository.save(impedimento);

    }

    public Iterable<Impedimento>exibirImpedimentos(){
        return impedimentoRepository.findAll();
    }


    public void deletarImpedimento(Long id){
        if(!impedimentoRepository.existsById(id)){
            throw new ImpedimentoInexistente("Impedimento não encontrado");

        }
        impedimentoRepository.deleteById(id);
    }

    public Impedimento alterarDescricaoImpedimento(Long id, ImpedimentoSaidaDTO impedimentoNovo) {

        Optional<Impedimento> impedimento = impedimentoRepository.findById(id);
        if (impedimento.isEmpty()){
            throw new ImpedimentoInexistente("Não existe impedimento vinculado a este ID");
        }
        Impedimento impedimentoParaAtualizar = impedimento.get();
        impedimentoParaAtualizar.setDescricao(impedimentoNovo.getDescricao());

        impedimentoRepository.save(impedimentoParaAtualizar);

        return impedimentoParaAtualizar;

    }


}
