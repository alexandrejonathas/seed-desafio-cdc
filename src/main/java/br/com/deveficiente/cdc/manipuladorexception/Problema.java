package br.com.deveficiente.cdc.manipuladorexception;

import java.time.OffsetDateTime;
import java.util.List;

public class Problema {
    private OffsetDateTime dataHora;
    private Integer status;
    private String titulo;
    private String detalhe;
    private List<Campo> campos;
    
    public Problema() {}

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
