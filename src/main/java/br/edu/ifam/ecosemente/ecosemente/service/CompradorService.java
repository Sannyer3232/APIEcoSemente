package br.edu.ifam.ecosemente.ecosemente.service;

import br.edu.ifam.ecosemente.ecosemente.dto.CompradorInputDTO;
import br.edu.ifam.ecosemente.ecosemente.dto.CompradorOutputDTO;
import br.edu.ifam.ecosemente.ecosemente.model.Comprador;
import br.edu.ifam.ecosemente.ecosemente.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompradorService {

    @Autowired
    private CompradorRepository compradorRepository;

    public List<CompradorOutputDTO> list() {
        try {
            List<Comprador> compradores = compradorRepository.findAll();
            List<CompradorOutputDTO> compradorDTOs = new ArrayList<>();

            for (Comprador comprador : compradores) {
                compradorDTOs.add(new CompradorOutputDTO(comprador));
            }

            return compradorDTOs;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CompradorOutputDTO getById(long id) {

        try {
            Comprador comprador = compradorRepository.findById(id).get();

            return new CompradorOutputDTO(comprador);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CompradorOutputDTO create(CompradorInputDTO compradorInputDTO) {

        try {
            return new CompradorOutputDTO(compradorRepository.save(compradorInputDTO.build()));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CompradorOutputDTO update(Long id,CompradorInputDTO compradorInputDTO) {

        try {
            Comprador comprador = compradorRepository.findById(id).get();

            comprador.setNome(compradorInputDTO.getNome());
            comprador.setEmail(compradorInputDTO.getEmail());
            comprador.setTelefone(compradorInputDTO.getTelefone());
            comprador.setEmail(compradorInputDTO.getEmail());

            return new CompradorOutputDTO(compradorRepository.save(comprador));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {

        try {
            compradorRepository.deleteById(id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
