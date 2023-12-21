package br.com.deveficiente.cdc.autor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.deveficiente.cdc.validacao.AtributoValorUnico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateAutorRequest {
    
    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @AtributoValorUnico(classe = Autor.class, atributo = "email")
    private String email;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    public CreateAutorRequest(@NotBlank String nome, @NotBlank @Email String email,
            @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }
    
    public Autor toModel() {
        return new Autor(nome, email, descricao);
    }

}
