package br.com.bruno.apo.retrofit;

import java.util.ArrayList;
import java.util.List;

public class DadosTempo {

    private String cidade;
    private String data;
    private int temperatura;
    private String descricao;
    private float chuva;
    private String velVento;
    private int umidade;
    private ArrayList<Forecast> lista = new ArrayList<>();

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getChuva() {
        return chuva;
    }

    public void setChuva(float chuva) {
        this.chuva = chuva;
    }

    public String getVelVento() {
        return velVento;
    }

    public void setVelVento(String velVento) {
        this.velVento = velVento;
    }

    public int getUmidade() {
        return umidade;
    }

    public void setUmidade(int umidade) {
        this.umidade = umidade;
    }

    public ArrayList<Forecast> getLista() {
        return lista;
    }

    public void setLista(List<Forecast> lista) {
        this.lista.addAll(lista);
    }
}
