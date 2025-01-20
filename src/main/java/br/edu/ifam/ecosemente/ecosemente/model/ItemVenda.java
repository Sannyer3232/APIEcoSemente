package br.edu.ifam.ecosemente.ecosemente.model;

import jakarta.persistence.*;

@Entity
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Semente semente;
    @Column(nullable = false)
    private int quantidade;
    @Column(nullable = false)
    private float precoItem;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Venda venda;

    public ItemVenda() {
    }

    public ItemVenda(long id, Semente semente, int quantidade, float precoItem, Venda venda) {
        this.id = id;
        this.semente = semente;
        this.quantidade = quantidade;
        this.precoItem = precoItem;
        this.venda = venda;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Semente getSemente() {
        return semente;
    }

    public void setSemente(Semente semente) {
        this.semente = semente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPrecoItem() {
        return precoItem;
    }

    public void setPrecoItem(float precoItem) {
        this.precoItem = precoItem;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
