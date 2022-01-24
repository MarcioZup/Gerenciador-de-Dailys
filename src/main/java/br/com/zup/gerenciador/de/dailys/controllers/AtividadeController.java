package br.com.zup.gerenciador.de.dailys.controllers;

import br.com.zup.gerenciador.de.dailys.model.Atividade;
import br.com.zup.gerenciador.de.dailys.services.AtividadeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {
    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Atividade cadastrarAtividade(@RequestBody @Valid Atividade atividade){
       Atividade novaAtividade = modelMapper.map(atividade, Atividade.class);
       return modelMapper.map(atividadeService.salvarAtividade(novaAtividade), Atividade.class);
    }

}
