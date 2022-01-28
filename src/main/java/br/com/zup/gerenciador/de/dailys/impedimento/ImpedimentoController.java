package br.com.zup.gerenciador.de.dailys.impedimento;

import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoSaidaDTO;
import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoEntradaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/impedimento")
public class ImpedimentoController {

    @Autowired
    private ImpedimentoService impedimentoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImpedimentoSaidaDTO cadastrarImpedimento(@RequestBody @Valid ImpedimentoEntradaDTO impedimentoEntradaDTO){
        Impedimento impedimento = impedimentoService.salvarImpedimento(impedimentoEntradaDTO.getEmailUsuario());
        impedimento.setDescricao(impedimentoEntradaDTO.getDescricao());
        return modelMapper.map(impedimento, ImpedimentoSaidaDTO.class);
    }

    @GetMapping
    public List<ImpedimentoSaidaDTO> exibirImpedimentos(){
        List<ImpedimentoSaidaDTO> listaDeImpedimentos = new ArrayList<>();
        for(Impedimento impedimentoReferencia: impedimentoService.exibirImpedimentos()){
            ImpedimentoSaidaDTO impedimentoDTO = modelMapper.map(impedimentoReferencia, ImpedimentoSaidaDTO.class);
            listaDeImpedimentos.add(impedimentoDTO);
        }
        return listaDeImpedimentos;
    }

    @PutMapping("/{id}")
    public ImpedimentoSaidaDTO atualizarImpedimento(@PathVariable Long id, @RequestBody ImpedimentoSaidaDTO impedimentoDTO){
            Impedimento impedimento = impedimentoService.alterarDescricaoImpedimento(id, impedimentoDTO);

            return modelMapper.map(impedimento, ImpedimentoSaidaDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarImpedimento(@PathVariable Long id){
        impedimentoService.deletarImpedimento(id);
    }
}
