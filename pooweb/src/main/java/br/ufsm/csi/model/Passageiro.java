package br.ufsm.csi.model;

public class Passageiro {
    private String nome;
    private String CPF;
    private String endereco;

    public Passageiro() {
    }

    public Passageiro(String nome, String CPF, String endereco) {
        this.nome = nome;
        this.CPF = CPF;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
