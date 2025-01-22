package br.edu.ifam.ecosemente.ecosemente.controller;

import br.edu.ifam.ecosemente.ecosemente.dto.CompradorInputDTO;
import br.edu.ifam.ecosemente.ecosemente.dto.CompradorOutputDTO;
import br.edu.ifam.ecosemente.ecosemente.dto.SementeInputDTO;
import br.edu.ifam.ecosemente.ecosemente.dto.SementeOutputDTO;
import br.edu.ifam.ecosemente.ecosemente.service.CompradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comprador")
@Tag(name="Comprador", description = "APIs para gerenciamento de comprador")
public class CompradorController {

    @Autowired
    private CompradorService compradorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar todos os compradores", description = "Retorna uma lista de todos os Compradores registrados")
    public ResponseEntity<List<CompradorOutputDTO>> findAll() {
        try {
            List<CompradorOutputDTO> compradorOutputDTOS = compradorService.list();
            if(!compradorOutputDTOS.isEmpty()) {
                return new ResponseEntity<>(compradorOutputDTOS, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @Operation(summary = "Buscar comprador por ID", description = "Retorna os detalhes de um compradores específico")
    public ResponseEntity<CompradorOutputDTO> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(compradorService.getById(id), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Inserir Comprador", description = "Insere os dados de Comprador e retornar o objeto salvo no banco de dados")
    public ResponseEntity<EntityModel<CompradorOutputDTO>> create(@RequestBody CompradorInputDTO compradorInputDTO, UriComponentsBuilder uriBuilder) {

        try{
            CompradorOutputDTO compradorOutputDTO = compradorService.create(compradorInputDTO);

            UriComponents uriComponents = uriBuilder.path("/api/semente/{id}").buildAndExpand(compradorOutputDTO.getId());

            URI uri = uriComponents.toUri();

            Link selflink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CompradorController.class).findById(compradorOutputDTO.getId())
            ).withSelfRel();

            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CompradorController.class).delete(compradorOutputDTO.getId())
            ).withRel("delete-link");

            EntityModel<CompradorOutputDTO> resource = EntityModel.of(compradorOutputDTO, selflink, deleteLink);

            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @Operation(summary = "Atualizar dados de Comprador", description = "Atualiza os dados de um Comprador específico e retorna o objeto atualizado")
    public ResponseEntity<EntityModel<CompradorOutputDTO>> update(@PathVariable("id") Long id, @RequestBody CompradorInputDTO compradorInputDTO, UriComponentsBuilder uriBuilder) {
        try{
            CompradorOutputDTO compradorOutputDTO = compradorService.update(id, compradorInputDTO);

            UriComponents uriComponents = uriBuilder.path("/api/semente/{id}").buildAndExpand(compradorOutputDTO.getId());

            URI uri = uriComponents.toUri();

            Link selflink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CompradorController.class).findById(compradorOutputDTO.getId())
            ).withSelfRel();

            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CompradorController.class).delete(compradorOutputDTO.getId())
            ).withRel("delete-link");

            EntityModel<CompradorOutputDTO> resource = EntityModel.of(compradorOutputDTO, selflink, deleteLink);

            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar comprador por ID", description = "Deletar um comprador em específico")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try{
            compradorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
