package br.edu.ifam.ecosemente.ecosemente.dto;

import br.edu.ifam.ecosemente.ecosemente.model.Semente;

public class SementeInputDTO {

    private String nome;
    private String descricao;
    private String especie;
    private String epocaPlantio;
    private int tempoMedioColheita;
    private int quantidade;
    private float preco;
    private String cuidado;


    public SementeInputDTO() {
    }

    public SementeInputDTO(String nome, String descricao, String especie, String epocaPlantio, int tempoMedioColheita, int quantidade, float preco, String cuidado) {
        this.nome = nome;
        this.descricao = descricao;
        this.especie = especie;
        this.epocaPlantio = epocaPlantio;
        this.tempoMedioColheita = tempoMedioColheita;
        this.quantidade = quantidade;
        this.preco = preco;
        this.cuidado = cuidado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getEpocaPlantio() {
        return epocaPlantio;
    }

    public void setEpocaPlantio(String epocaPlantio) {
        this.epocaPlantio = epocaPlantio;
    }

    public int getTempoMedioColheita() {
        return tempoMedioColheita;
    }

    public void setTempoMedioColheita(int tempoMedioColheita) {
        this.tempoMedioColheita = tempoMedioColheita;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getCuidado() {
        return cuidado;
    }

    public void setCuidado(String cuidado) {
        this.cuidado = cuidado;
    }

    public Semente build(){
        Semente semente = new Semente();

        semente.setNome(this.nome);
        semente.setDescricao(this.descricao);
        semente.setEspecie(this.especie);
        semente.setEpocaPlantio(this.epocaPlantio);
        semente.setTempoMedioColheita(this.tempoMedioColheita);
        semente.setQuantidade(this.quantidade);
        semente.setPreco(this.preco);
        semente.setCuidado(this.cuidado);

        return semente;
    }

}
