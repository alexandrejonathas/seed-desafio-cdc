package br.com.deveficiente.cdc.manipuladorexception;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

// Total da carga intrínseca 2
public class ProblemaBuilder {
    
    private OffsetDateTime dataHora;
    private Integer status;
    private String titulo;
    private String detalhe;
    private String mensagem;
    //1 Acoplamento contextual classe Campo
    private List<Campo> campos;

    public ProblemaBuilder(Integer status, String titulo) {
        this.dataHora = OffsetDateTime.now();
        this.status = status;
        this.titulo = titulo;
        this.campos = new ArrayList<>();
    }

    //1 Acoplamento contextual classe Problema
    public Problema build() {
        return new Problema(this);
    }

    public ProblemaBuilder comDetalhe(String detalhe) {
        this.detalhe = detalhe;
        return this;
    }

    public ProblemaBuilder comMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }
    
    public ProblemaBuilder comCampos(List<Campo> campos) {
        this.campos = campos;
        return this;
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

    public String getMensagem() {
        return mensagem;
    }

    public List<Campo> getCampos() {
        return campos;
    }
    
}
