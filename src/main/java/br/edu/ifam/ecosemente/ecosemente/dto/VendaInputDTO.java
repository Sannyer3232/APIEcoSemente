package br.edu.ifam.ecosemente.ecosemente.dto;

import br.edu.ifam.ecosemente.ecosemente.model.ItemVenda;
import br.edu.ifam.ecosemente.ecosemente.model.Semente;
import br.edu.ifam.ecosemente.ecosemente.model.Venda;
import br.edu.ifam.ecosemente.ecosemente.repository.CompradorRepository;
import br.edu.ifam.ecosemente.ecosemente.repository.SementeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaInputDTO {

    private LocalDate dataVenda;
    private String compradorCpfCnpj;
    private List<ItemVendaInputDTO> itens;


    public VendaInputDTO() {}

    public VendaInputDTO(LocalDate dataVenda, String comprador, List<ItemVendaInputDTO> itens) {
        this.dataVenda = dataVenda;
        this.compradorCpfCnpj = comprador;
        this.itens = itens;

    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getCompradorCpfCnpj() {
        return compradorCpfCnpj;
    }

    public void setCompradorCpfCnpj(String compradorCpfCnpj) {
        this.compradorCpfCnpj = compradorCpfCnpj;
    }

    public List<ItemVendaInputDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaInputDTO> itens) {
        this.itens = itens;
    }

    public Venda build(CompradorRepository compradorRepository, SementeRepository sementeRepository) {

        Venda venda = new Venda();
        venda.setDataVenda(this.dataVenda);
        venda.setComprador(compradorRepository.findByCpfCnpj(this.compradorCpfCnpj));

        List<ItemVenda> itens = new ArrayList<>();

        for(ItemVendaInputDTO itemVendaInputDTO : this.itens){
            itens.add(itemVendaInputDTO.build(sementeRepository));
        }

        venda.setItens(itens);
        float valorTotal = 0;

        for(ItemVenda itemVenda : itens){
            valorTotal += itemVenda.getPrecoItem();
        }
        venda.setValorTotal(valorTotal);

        return venda;
    }
}
