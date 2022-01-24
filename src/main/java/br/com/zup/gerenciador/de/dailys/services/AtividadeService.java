package br.com.zup.gerenciador.de.dailys.services;

import br.com.zup.gerenciador.de.dailys.model.Atividade;
import br.com.zup.gerenciador.de.dailys.repositories.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public Atividade salvarAtividade(Atividade atividade){
        return atividadeRepository.save(atividade);
    }

}
