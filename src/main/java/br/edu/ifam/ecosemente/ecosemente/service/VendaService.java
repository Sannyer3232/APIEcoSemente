package br.edu.ifam.ecosemente.ecosemente.service;

import br.edu.ifam.ecosemente.ecosemente.dto.VendaInputDTO;
import br.edu.ifam.ecosemente.ecosemente.dto.VendaOutputDTO;
import br.edu.ifam.ecosemente.ecosemente.model.ItemVenda;
import br.edu.ifam.ecosemente.ecosemente.model.Semente;
import br.edu.ifam.ecosemente.ecosemente.model.Venda;
import br.edu.ifam.ecosemente.ecosemente.repository.CompradorRepository;
import br.edu.ifam.ecosemente.ecosemente.repository.ItemVendaRepository;
import br.edu.ifam.ecosemente.ecosemente.repository.SementeRepository;
import br.edu.ifam.ecosemente.ecosemente.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private CompradorRepository compradorRepository;
    @Autowired
    private SementeRepository sementeRepository;
    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public List<VendaOutputDTO> list(){
        try{
            List<Venda> vendas = vendaRepository.findAll();
            List<VendaOutputDTO> vendaOutputDTOs = new ArrayList<>();

            for(Venda venda : vendas){
                vendaOutputDTOs.add(new VendaOutputDTO(venda));
            }

            return vendaOutputDTOs;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public VendaOutputDTO getById(long id){
        try {
            return new VendaOutputDTO(vendaRepository.findById(id).get());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public VendaOutputDTO create(VendaInputDTO vendaInputDTO){
        try {
            Venda venda = vendaInputDTO.build(compradorRepository,sementeRepository);

            for(ItemVenda itemVenda : venda.getItens()){
                Semente semente = sementeRepository.findById(itemVenda.getSemente().getId()).orElseThrow(
                        () -> new RuntimeException("Semente não encontrada: " + itemVenda.getSemente().getId())
                );

                if(semente.getQuantidade() < itemVenda.getQuantidade()){
                    throw new RuntimeException(
                            "Estoque insuficiente para semente: " + semente.getNome() + ".\n" +
                            "Disposnivel: " + semente.getQuantidade() + ".\n" +
                            "Solicitado: " + itemVenda.getQuantidade());
                }

                semente.setQuantidade(semente.getQuantidade() - itemVenda.getQuantidade());
                sementeRepository.save(semente);
            }
            venda.addVenda();
        return new VendaOutputDTO(vendaRepository.save(venda));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public VendaOutputDTO update(Long id, VendaInputDTO vendaInputDTO) {
        try {
            Venda vendaExistente = vendaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada com ID: " + id));
            Venda vendaUpadate = vendaInputDTO.build(compradorRepository, sementeRepository);


            for (ItemVenda itemVenda : vendaExistente.getItens()) {
                Semente semente = sementeRepository.findById(itemVenda.getSemente().getId())
                        .orElseThrow(() -> new RuntimeException("Semente não encontrada: " + itemVenda.getSemente().getId()));

                semente.setQuantidade(semente.getQuantidade() + itemVenda.getQuantidade());
                sementeRepository.save(semente);
            }


            vendaExistente.getItens().clear();
            for (ItemVenda itemVenda : vendaUpadate.getItens()) {
                Semente semente = sementeRepository.findById(itemVenda.getSemente().getId())
                        .orElseThrow(() -> new RuntimeException("Semente não encontrada: " + itemVenda.getSemente().getId()));

                if (semente.getQuantidade() < itemVenda.getQuantidade()) {
                    throw new RuntimeException(
                            "Estoque insuficiente para semente: " + semente.getNome() + ".\n" +
                                    "Disponível: " + semente.getQuantidade() + ".\n" +
                                    "Solicitado: " + itemVenda.getQuantidade());
                }

                semente.setQuantidade(semente.getQuantidade() - itemVenda.getQuantidade());
                sementeRepository.save(semente);

                itemVenda.setVenda(vendaExistente);
                vendaExistente.getItens().add(itemVenda);
            }

            // Atualizar demais campos da venda
            vendaExistente.setComprador(vendaUpadate.getComprador());
            vendaExistente.setDataVenda(vendaUpadate.getDataVenda());
            vendaExistente.setValorTotal(vendaUpadate.getValorTotal());

            vendaExistente.addVenda();

            return new VendaOutputDTO(vendaRepository.save(vendaExistente));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        try {
            Venda venda = vendaRepository.findById(id).get();
            List<ItemVenda> itemVendas = venda.getItens();
            itemVendaRepository.deleteAll(itemVendas);
            vendaRepository.delete(venda);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
