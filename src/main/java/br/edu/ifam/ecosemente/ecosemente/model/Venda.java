package br.edu.ifam.ecosemente.ecosemente.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDate dataVenda;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Comprador comprador;
    @OneToMany
    @JoinColumn(nullable = false)
    private List<ItemVenda> itens;
    @Column(nullable = false)
    private float valorTotal;

    public Venda() {
    }

    public Venda(long id, LocalDate dataVenda, Comprador comprador, List<ItemVenda> itens, float valorTotal) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.comprador = comprador;
        this.itens = itens;
        this.valorTotal = valorTotal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
}
