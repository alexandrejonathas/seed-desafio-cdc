package br.com.deveficiente.cdc.manipuladorexception;

public class Campo {
    private String nome;
    private String mensagem;

    public Campo(String nome, String mensagem) {
        this.nome = nome;
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public String getMensagem() {
        return mensagem;
    }

}
