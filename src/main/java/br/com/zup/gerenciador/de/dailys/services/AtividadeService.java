package br.com.zup.gerenciador.de.dailys.services;

import br.com.zup.gerenciador.de.dailys.exceptions.AtividadeNaoEncontrada;
import br.com.zup.gerenciador.de.dailys.model.Atividade;
import br.com.zup.gerenciador.de.dailys.repositories.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public Atividade salvarAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public void deletarAtividade(Long id) {
        if (!atividadeRepository.existsById(id)) {
            throw new AtividadeNaoEncontrada("Atividade não encontrada");
        }
        atividadeRepository.deleteById(id);
    }

   public Atividade exibirAtividade(){
        Atividade atividade = (Atividade) atividadeRepository.findAll();
        return atividade;
   }

   public Atividade buscarAtividadePorId(Long id){
        Optional<Atividade>atividadeOptional = atividadeRepository.findById(id);
        if(atividadeOptional.isEmpty()){
            throw new AtividadeNaoEncontrada("Atividade não encontrada");
        }
        return atividadeOptional.get();
   }

}
