package br.edu.ifam.ecosemente.ecosemente.dto;


import br.edu.ifam.ecosemente.ecosemente.model.Comprador;
import br.edu.ifam.ecosemente.ecosemente.model.ItemVenda;
import br.edu.ifam.ecosemente.ecosemente.model.Venda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaOutputDTO {

    private long id;
    private LocalDate dataVenda;
    private String comprador;
    private List<ItemVendaOutputDTO> itens;
    private float valorTotal;

    public VendaOutputDTO() {}

    public VendaOutputDTO(Venda venda) {
        this.id = venda.getId();
        this.dataVenda = venda.getDataVenda();
        this.comprador = venda.getComprador().getNome();

        List<ItemVendaOutputDTO> itens = new ArrayList<>();

        for (ItemVenda itemVenda : venda.getItens()) {
            itens.add(new ItemVendaOutputDTO(itemVenda));
        }
        this.itens = itens ;
        this.valorTotal = venda.getValorTotal();
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

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public List<ItemVendaOutputDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaOutputDTO> itens) {
        this.itens = itens;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
}
