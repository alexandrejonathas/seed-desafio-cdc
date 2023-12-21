package br.com.deveficiente.cdc.manipuladorexception;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *  Classe criada visando retornar erros num padrão específico, e facilitar a vida de quam consome a API.
 */
@ControllerAdvice
public class ManipuladorExceptionController extends ResponseEntityExceptionHandler {
    
    /**
     * Adicionado o handle para exibir uma mensagem que faça sentido para o cliente que está consumindo a API, 
     * já que foi adicionando um index de unicidade para o atributo email no banco de dados para evitar problemas de concorrencia.
    */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handlerConstraintViolationException(ConstraintViolationException ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String titulo = "Ocorreu uma violação de restrição do banco de dados";
        ProblemaBuilder builder = new ProblemaBuilder(status.value(), titulo).comDetalhe(ex.getLocalizedMessage());

        return ResponseEntity.status(status.value()).body(builder.build());
    }

    /**
     * Com essa tratativa além de retornar o status de requisição inválida, 
     * exibimos para o cliente uma mensagem dizendo o que ele precisa corrigir para realizar uma nova requisição. 
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        BindingResult bindingResult = ex.getBindingResult();

        return handleInternalValidation(ex, bindingResult, headers, status, request);
    }

    private ResponseEntity<Object> handleInternalValidation(Exception ex, BindingResult bindingResult, 
        HttpHeaders headers, HttpStatusCode httpStatus, WebRequest webRequest) {
        
        ProblemaBuilder builder = new ProblemaBuilder(httpStatus.value(), "Dados inválidos");
        builder.comDetalhe("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");

        List<Campo> campos = bindingResult.getAllErrors().stream().map(erro -> {
            String mensagem = erro.getDefaultMessage();
            String nome = erro.getObjectName();

            if (erro instanceof FieldError) {
                nome = ((FieldError) erro).getField();
            }

            return new Campo(nome, mensagem);
        }).collect(Collectors.toList());     

        builder.comCampos(campos);

        Problema problema = builder.build();

        return super.handleExceptionInternal(ex, problema, headers, httpStatus, webRequest);
    }
}
