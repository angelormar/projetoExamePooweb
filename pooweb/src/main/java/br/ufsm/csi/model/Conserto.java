package br.ufsm.csi.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Conserto {
    private int idConserto;
    private String placa;
    private int idOficina;
    private Date data;

    public Conserto() {
    }

    public Conserto(int idConserto, String placa, int idOficina, Date data) {
        this.idConserto = idConserto;
        this.placa = placa;
        this.idOficina = idOficina;
        this.data = data;
    }

    public int getIdConserto() {
        return idConserto;
    }

    public void setIdConserto(int idConserto) {
        this.idConserto = idConserto;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
