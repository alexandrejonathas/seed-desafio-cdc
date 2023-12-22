package br.com.deveficiente.cdc.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.ISBN;

import br.com.deveficiente.cdc.autor.Autor;
import br.com.deveficiente.cdc.categoria.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
// Total da carga intrínseca 2
public class Livro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String titulo;

    @NotBlank
    @Column(columnDefinition = "TEXT", length = 500)
    private String resumo;

    @Column(columnDefinition = "TEXT")
    private String sumario;

    @NotNull
    @DecimalMin(value = "20.00")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal preco;

    @Positive
    @Min(value = 100)
    private Integer numeroPaginas;

    @ISBN
    @NotBlank
    @Column(unique = true)
    private String isbn;

    @Future
    private LocalDate dataPublicacao;

    @NotNull
    @ManyToOne
    // 1 Acoplamento contextual classe categoria
    private Categoria categoria;

    @NotNull
    @ManyToOne
    // 1 Acoplamento contextual classe autor
    private Autor autor;
    
    @Deprecated
    public Livro() {}

    public Livro(@NotBlank String titulo, @NotBlank String resumo, String sumario,
            @NotNull @DecimalMin("20.00") @Digits(integer = 3, fraction = 2) BigDecimal preco,
            @Positive @Min(100) Integer numeroPaginas, @NotBlank String isbn, @Future LocalDate dataPublicacao,
            @NotNull Categoria categoria, @NotNull Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
