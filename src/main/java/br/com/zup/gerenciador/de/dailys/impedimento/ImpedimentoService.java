package br.com.zup.gerenciador.de.dailys.impedimento;

import br.com.zup.gerenciador.de.dailys.impedimento.Impedimento;
import br.com.zup.gerenciador.de.dailys.impedimento.ImpedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Impedimento atualizarImpedimento(Impedimento impedimento){
        return  impedimentoRepository.save(impedimento);
    }

    public void deletarImpedimento(Long id){
        impedimentoRepository.deleteById(id);
    }
}
