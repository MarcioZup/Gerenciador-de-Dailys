package br.com.zup.gerenciador.de.dailys.controllers;

import br.com.zup.gerenciador.de.dailys.model.Atividade;
import br.com.zup.gerenciador.de.dailys.repositories.AtividadeRepository;
import br.com.zup.gerenciador.de.dailys.services.AtividadeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {
    @Autowired
    private AtividadeService atividadeService;


    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Atividade cadastrarAtividade(@RequestBody @Valid Atividade atividade) {
        Atividade novaAtividade = modelMapper.map(atividade, Atividade.class);
        return modelMapper.map(atividadeService.salvarAtividade(novaAtividade), Atividade.class);
    }

    @GetMapping
    public Iterable<Atividade> exibirAtividades(){
        return atividadeService.exibirAtividades();
    }


}
