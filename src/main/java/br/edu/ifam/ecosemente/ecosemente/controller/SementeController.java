package br.edu.ifam.ecosemente.ecosemente.controller;

import br.edu.ifam.ecosemente.ecosemente.dto.SementeInputDTO;
import br.edu.ifam.ecosemente.ecosemente.dto.SementeOutputDTO;
import br.edu.ifam.ecosemente.ecosemente.service.SementeService;
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

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/semente")
@Tag(name="Semente", description = "APIs para gerenciamento de semente")
public class SementeController {


    @Autowired
    private SementeService sementeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar todas as Sementes", description = "Retorna uma lista de todos as Sementes registradas")
    public ResponseEntity<List<SementeOutputDTO>> findAll() {
        try {
            List<SementeOutputDTO> sementeOutputDTOS = sementeService.list();
            if(!sementeOutputDTOS.isEmpty()) {
                return new ResponseEntity<>(sementeOutputDTOS, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @Operation(summary = "Buscar semente por ID", description = "Retorna os detalhes de uma semente específica")
    public ResponseEntity<SementeOutputDTO> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(sementeService.getById(id), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Inserir semente", description = "Insere os dados de semente e retornar o objeto salvo no banco de dados")
    public ResponseEntity<EntityModel<SementeOutputDTO>> create(@RequestBody SementeInputDTO sementeInputDTO, UriComponentsBuilder uriBuilder) {

        try{
            SementeOutputDTO sementeOutputDTO = sementeService.create(sementeInputDTO);

            UriComponents uriComponents = uriBuilder.path("/api/semente/{id}").buildAndExpand(sementeOutputDTO.getId());

            URI uri = uriComponents.toUri();

            Link selflink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(SementeController.class).findById(sementeOutputDTO.getId())
            ).withSelfRel();

            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(SementeController.class).delete(sementeOutputDTO.getId())
            ).withRel("delete-link");

            EntityModel<SementeOutputDTO> resource = EntityModel.of(sementeOutputDTO, selflink, deleteLink);

            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @Operation(summary = "Atualizar dados de Semente", description = "Atualiza os dados de uma Semente específica e retorna o objeto atualizado")
    public ResponseEntity<EntityModel<SementeOutputDTO>> update(@PathVariable("id") Long id, @RequestBody SementeInputDTO sementeInputDTO, UriComponentsBuilder uriBuilder) {
        try{
            SementeOutputDTO sementeOutputDTO = sementeService.update(id, sementeInputDTO);

            UriComponents uriComponents = uriBuilder.path("/api/semente/{id}").buildAndExpand(sementeOutputDTO.getId());

            URI uri = uriComponents.toUri();

            Link selflink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(SementeController.class).findById(sementeOutputDTO.getId())
            ).withSelfRel();

            Link deleteLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(SementeController.class).delete(sementeOutputDTO.getId())
            ).withRel("delete-link");

            EntityModel<SementeOutputDTO> resource = EntityModel.of(sementeOutputDTO, selflink, deleteLink);

            return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar semente por ID", description = "Deletar uma semente  em específico")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try{
            sementeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
