package br.com.zup.gerenciador.de.dailys.impedimento;

import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoDTO;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioService;
import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImpedimentoService {
    @Autowired
    private ImpedimentoRepository impedimentoRepository;

    public Impedimento salvarImpedimento(Impedimento impedimento){
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

    public Impedimento alterarDescricaoImpedimento(Long id, ImpedimentoDTO impedimentoNovo) {

        Optional<Impedimento> impedimento = impedimentoRepository.findByiD(id);
        if (impedimento.isEmpty()){
            throw new ImpedimentoInexistente("Não existe impedimento vinculado a este ID");
        }
        Impedimento impedimentoParaAtualizar = impedimento.get();
        impedimentoParaAtualizar.setDescricao(impedimentoNovo.getDescricao());

        impedimentoRepository.save(impedimentoParaAtualizar);

        return impedimentoParaAtualizar;

    }

}
