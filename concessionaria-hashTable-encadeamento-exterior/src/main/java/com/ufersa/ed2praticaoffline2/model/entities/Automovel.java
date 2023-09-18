package com.ufersa.ed2praticaoffline1.model.entities;

public class Automovel {


    private String placa;
    private String renavam;
    private String modelo;
    private String dataFabricacao;
    private Condutor condutor;

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    @Override
    public String toString() {
        return "Automovel{" +
                "placa='" + placa + '\'' +
                ", renavam='" + renavam + '\'' +
                ", modelo='" + modelo + '\'' +
                ", dataFabricacao='" + dataFabricacao + '\'' +
                ", condutor=" + condutor +
                '}';
    }
}
