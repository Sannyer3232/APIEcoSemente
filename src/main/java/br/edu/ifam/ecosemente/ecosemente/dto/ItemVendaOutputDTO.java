package br.edu.ifam.ecosemente.ecosemente.dto;

import br.edu.ifam.ecosemente.ecosemente.model.ItemVenda;
import br.edu.ifam.ecosemente.ecosemente.model.Semente;
import jakarta.persistence.*;

public class ItemVendaOutputDTO {

    private long id;
    private String semente;
    private int quantidade;
    private float precoItem;


    public ItemVendaOutputDTO() {
    }

    public ItemVendaOutputDTO(ItemVenda itemVenda) {
        this.id = itemVenda.getId();
        this.semente = itemVenda.getSemente().getNome();
        this.quantidade = itemVenda.getQuantidade();
        this.precoItem = itemVenda.getPrecoItem();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSemente() {
        return semente;
    }

    public void setSemente(String semente) {
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
}
