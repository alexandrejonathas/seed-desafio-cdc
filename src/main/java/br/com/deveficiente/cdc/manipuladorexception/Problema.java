package br.com.deveficiente.cdc.manipuladorexception;

import java.time.OffsetDateTime;
import java.util.List;

// Total da carga intrínseca 2
public class Problema {
    private OffsetDateTime dataHora;
    private Integer status;
    private String titulo;
    private String detalhe;
    //1 Acoplamento contextual classe Campo
    private List<Campo> campos;

    //1 Acoplamento contextual classe PloblemaBuilder
    public Problema(ProblemaBuilder builder) {
        this.dataHora = builder.getDataHora();
        this.status = builder.getStatus();
        this.titulo = builder.getTitulo();
        this.detalhe = builder.getDetalhe();
        this.campos = builder.getCampos();
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public Integer getStatus() {
        return status;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public List<Campo> getCampos() {
        return campos;
    }

}
