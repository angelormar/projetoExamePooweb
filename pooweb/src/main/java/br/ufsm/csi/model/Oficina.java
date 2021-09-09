package br.ufsm.csi.model;

public class Oficina {
    private int id;
    private String endereco;
    private String nome;

    public Oficina() {
    }

    public Oficina(int id, String endereco, String nome) {
        this.id = id;
        this.endereco = endereco;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
