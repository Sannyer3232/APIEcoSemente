package br.edu.ifam.ecosemente.ecosemente.controller;

import br.edu.ifam.ecosemente.ecosemente.dto.SementeInputDTO;
import br.edu.ifam.ecosemente.ecosemente.dto.SementeOutputDTO;
import br.edu.ifam.ecosemente.ecosemente.dto.VendaInputDTO;
import br.edu.ifam.ecosemente.ecosemente.dto.VendaOutputDTO;
import br.edu.ifam.ecosemente.ecosemente.service.VendaService;
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
@RequestMapping("/api/venda")
@Tag(name="Venda", description = "APIs para gerenciamento de venda")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar todas as Vendas", description = "Retorna uma lista de todos as Vendas registradas")
    public ResponseEntity<List<VendaOutputDTO>> findAll() {
        try {
            List<VendaOutputDTO> vendaOutputDTOS = vendaService.list();
            if(!vendaOutputDTOS.isEmpty()) {
                return new ResponseEntity<>(vendaOutputDTOS, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @Operation(summary = "Buscar venda por ID", description = "Retorna os detalhes de uma venda específica")
    public ResponseEntity<VendaOutputDTO> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(vendaService.getById(id), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Inserir venda", description = "Insere os dados de venda e retornar o objeto salvo no banco de dados")
    public ResponseEntity<EntityModel<VendaOutputDTO>> create(@RequestBody VendaInputDTO vendaInputDTO, UriComponentsBuilder uriBuilder) {

        try{
            VendaOutputDTO vendaOutputDTO = vendaService.create(vendaInputDTO);

            UriComponents uriComponents = uriBuilder.path("/api/semente/{id}").buildAndExpand(vendaOutputDTO.getId());

            URI uri = uriComponents.toUri();

            Link selflink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(SementeController.class).findById(vendaOutputDTO.getId())
            ).withSelfRel();

            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(SementeController.class).delete(vendaOutputDTO.getId())
            ).withRel("delete-link");

            EntityModel<VendaOutputDTO> resource = EntityModel.of(vendaOutputDTO, selflink, deleteLink);

            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @Operation(summary = "Atualizar dados de Venda", description = "Atualiza os dados de uma venda específica e retorna o objeto atualizado")
    public ResponseEntity<EntityModel<VendaOutputDTO>> update(@PathVariable("id") Long id, @RequestBody VendaInputDTO vendaInputDTO, UriComponentsBuilder uriBuilder) {
        try{
            VendaOutputDTO vendaOutputDTO = vendaService.update(id, vendaInputDTO);

            UriComponents uriComponents = uriBuilder.path("/api/semente/{id}").buildAndExpand(vendaOutputDTO.getId());

            URI uri = uriComponents.toUri();

            Link selflink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(SementeController.class).findById(vendaOutputDTO.getId())
            ).withSelfRel();

            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(SementeController.class).delete(vendaOutputDTO.getId())
            ).withRel("delete-link");

            EntityModel<VendaOutputDTO> resource = EntityModel.of(vendaOutputDTO, selflink, deleteLink);

            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar venda por ID", description = "Deletar uma venda em específico")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try{
            vendaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
