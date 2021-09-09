package br.ufsm.csi.model;

public class Aviao {
    private String placa;
    private String modelo;
    private String fabricante;

    public Aviao() {
    }

    public Aviao(String placa, String modelo, String fabricante) {

        this.placa = placa;
        this.modelo = modelo;
        this.fabricante = fabricante;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
}
