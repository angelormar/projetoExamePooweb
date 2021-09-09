package br.ufsm.csi.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Viagem {
    private int codigo;
    private String placa;
    private Timestamp data;
    private Time hora;
    private String origem;
    private String destino;

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Viagem() {
    }

    public Viagem(int codigo, String placa, Timestamp data, Time hora, String origem, String destino) {
        this.codigo = codigo;
        this.placa = placa;
        this.data = data;
        this.hora = hora;
        this.origem = origem;
        this.destino = destino;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
}
