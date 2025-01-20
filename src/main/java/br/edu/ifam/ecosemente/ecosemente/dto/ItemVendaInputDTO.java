package br.edu.ifam.ecosemente.ecosemente.dto;

import br.edu.ifam.ecosemente.ecosemente.model.ItemVenda;
import br.edu.ifam.ecosemente.ecosemente.model.Semente;
import br.edu.ifam.ecosemente.ecosemente.repository.SementeRepository;

public class ItemVendaInputDTO {

    private String semente;
    private int quantidade;

    public ItemVendaInputDTO() {
    }

    public ItemVendaInputDTO(String semente, int quantidade) {
        this.semente = semente;
        this.quantidade = quantidade;
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

    public ItemVenda build(SementeRepository sementeRepository){
        ItemVenda itemVenda = new ItemVenda();
        Semente semente = sementeRepository.findByNome(this.semente);
        itemVenda.setSemente(semente);
        itemVenda.setQuantidade(this.quantidade);
        itemVenda.setPrecoItem(this.quantidade*semente.getPreco());
        return itemVenda;
    }
}
