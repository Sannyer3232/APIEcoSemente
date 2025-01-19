package br.edu.ifam.ecosemente.ecosemente.service;

import br.edu.ifam.ecosemente.ecosemente.dto.SementeInputDTO;
import br.edu.ifam.ecosemente.ecosemente.dto.SementeOutputDTO;
import br.edu.ifam.ecosemente.ecosemente.model.Semente;
import br.edu.ifam.ecosemente.ecosemente.repository.SementeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SementeService {

    @Autowired
    private SementeRepository sementeRepository;

    public List<SementeOutputDTO> list(){

        try{
            List<Semente> sementes = sementeRepository.findAll();
            List<SementeOutputDTO> sementeOutputDTOS = new ArrayList<>();

            for (Semente semente : sementes){
                sementeOutputDTOS.add(new SementeOutputDTO(semente));
            }

            return sementeOutputDTOS;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public SementeOutputDTO getById(Long id){
        try {
            return new SementeOutputDTO(sementeRepository.findById(id).get());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public SementeOutputDTO create(SementeInputDTO sementeInputDTO){
        try {
            return new SementeOutputDTO(sementeRepository.save(sementeInputDTO.build()));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public SementeOutputDTO update(Long id, SementeInputDTO sementeInputDTO){
        try{

            Semente semente = sementeRepository.findById(id).get();

            semente.setNome(sementeInputDTO.getNome());
            semente.setDescricao(sementeInputDTO.getDescricao());
            semente.setEspecie(sementeInputDTO.getEspecie());
            semente.setEpocaPlantio(sementeInputDTO.getEpocaPlantio());
            semente.setTempoMedioColheita(sementeInputDTO.getTempoMedioColheita());
            semente.setQuantidade(sementeInputDTO.getQuantidade());
            semente.setPreco(sementeInputDTO.getPreco());
            semente.setCuidado(sementeInputDTO.getCuidado());

            return new SementeOutputDTO(sementeRepository.save(semente));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        try {
            sementeRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
