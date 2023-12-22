package br.com.deveficiente.cdc.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.ISBN;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.deveficiente.cdc.autor.Autor;
import br.com.deveficiente.cdc.categoria.Categoria;
import br.com.deveficiente.cdc.validacao.AtributoValorUnico;
import br.com.deveficiente.cdc.validacao.EntidadeExistente;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateLivroRequest {

    @JsonProperty
    @NotBlank
    @AtributoValorUnico(classe = Livro.class, atributo = "titulo")
    private String titulo;

    @JsonProperty
    @NotBlank
    @Size(max = 500)
    private String resumo;

    @JsonProperty
    private String sumario;

    @JsonProperty
    @NotNull
    @DecimalMin(value = "20.00")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal preco;

    @JsonProperty
    @Positive
    @Min(value = 100)
    private Integer numeroPaginas;

    @JsonProperty
    @ISBN
    @NotBlank
    @AtributoValorUnico(classe = Livro.class, atributo = "isbn")
    private String isbn;

    @JsonProperty
    @Future
    private LocalDate dataPublicacao;

    @JsonProperty
    @NotNull
    @EntidadeExistente(classe = Categoria.class)
    private Long categoriaId;

    @JsonProperty
    @NotNull
    @EntidadeExistente(classe = Autor.class)
    private Long autorId;

    @JsonCreator
    public CreateLivroRequest(@NotBlank String titulo, @NotBlank @Max(500) String resumo, String sumario,
            @NotNull @DecimalMin("20.00") @Digits(integer = 3, fraction = 2) BigDecimal preco,
            @Positive @Min(100) Integer numeroPaginas, @NotBlank String isbn, @Future LocalDate dataPublicacao,
            @NotNull Long categoriaId, @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }
    
    public Livro toModel(EntityManager manager) {
		var categoria = manager.find(Categoria.class, categoriaId);
		var autor = manager.find(Autor.class, autorId);
		return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);
    }
}
