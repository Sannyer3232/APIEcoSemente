package br.edu.ifam.ecosemente.ecosemente.model;

import jakarta.persistence.*;

@Entity
public class Semente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false, unique = true)
    private String especie;
    @Column(nullable = false)
    private String epocaPlantio;
    @Column(nullable = false)
    private int tempoMedioColheita;
    @Column(nullable = false)
    private int quantidade;
    @Column(nullable = false)
    private float preco;
    @Column(nullable = false)
    private String cuidado;


    public Semente() {
    }

    public Semente(long id, String nome, String descricao, String especie, String epocaPlantio, int tempoMedioColheita, int quantidade, float preco, String cuidado) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.especie = especie;
        this.epocaPlantio = epocaPlantio;
        this.tempoMedioColheita = tempoMedioColheita;
        this.quantidade = quantidade;
        this.preco = preco;
        this.cuidado = cuidado;
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
