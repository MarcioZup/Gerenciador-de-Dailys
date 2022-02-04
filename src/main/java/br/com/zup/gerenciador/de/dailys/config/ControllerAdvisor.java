package br.com.zup.gerenciador.de.dailys.config;

import br.com.zup.gerenciador.de.dailys.impedimento.ImpedimentoInexistente;
import br.com.zup.gerenciador.de.dailys.proximaTask.exception.ProximaTaskInexistente;
import br.com.zup.gerenciador.de.dailys.taskAtual.TaskAtualInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.DominioNaoPermitidoException;
import br.com.zup.gerenciador.de.dailys.usuario.EmailCadastrado;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioInexistente;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ErroValidacao> tratarErrosDeValidacao(MethodArgumentNotValidException excecao) {
        List<ErroValidacao> erros = new ArrayList<>();

        for (FieldError referencia : excecao.getFieldErrors()) {
            ErroValidacao erroValidacao = new ErroValidacao(referencia.getDefaultMessage(), referencia.getField());
            erros.add(erroValidacao);
        }

        return erros;
    }

    @ExceptionHandler(UsuarioInexistente.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String retornoIdNaoEncontrado(UsuarioInexistente exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(EmailCadastrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String retornoIdNaoEncontrado(EmailCadastrado exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(DominioNaoPermitidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String retornoIdNaoEncontrado(DominioNaoPermitidoException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(TaskAtualInexistente.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String retornoIdNaoEncontrado(TaskAtualInexistente exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(ImpedimentoInexistente.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String retornoStatusDiferente(ImpedimentoInexistente exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(ProximaTaskInexistente.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String retornoStatusDiferente(ProximaTaskInexistente exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroValidacao manipularErroDeTipoNull(HttpMessageNotReadableException exception) {
        ErroValidacao erroValidacao = new ErroValidacao("Campo preenchido incorretamente", "tipoDeConta");

        return erroValidacao;
    }

}
