package br.ufsm.csi.model;

public class Bagagem {
        private int id;
        private float peso;
        private Aviao aviao;
        private Passageiro passageiro;

    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }

    public Bagagem(int id, float peso, Aviao aviao, Passageiro passageiro) {
        this.id = id;
        this.peso = peso;
        this.aviao = aviao;
        this.passageiro = passageiro;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Bagagem() {
    }

    public Bagagem(int id, float peso) {
        this.id = id;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
}
