package br.edu.ifam.ecosemente.ecosemente.dto;


import br.edu.ifam.ecosemente.ecosemente.model.Semente;

public class SementeOutputDTO {

    private long id;
    private String nome;
    private String descricao;
    private String especie;
    private String epocaPlantio;
    private int tempoMedioColheita;
    private int quantidade;
    private float preco;
    private String cuidado;


    public SementeOutputDTO() {
    }

    public SementeOutputDTO(Semente semente) {
        this.id = semente.getId();
        this.nome = semente.getNome();
        this.descricao = semente.getDescricao();
        this.especie = semente.getEspecie();
        this.epocaPlantio = semente.getEpocaPlantio();
        this.tempoMedioColheita = semente.getTempoMedioColheita();
        this.quantidade = semente.getQuantidade();
        this.preco = semente.getPreco();
        this.cuidado = semente.getCuidado();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
