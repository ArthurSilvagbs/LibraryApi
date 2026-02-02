package io.github.arthursilva.libraryapi.controller;

import io.github.arthursilva.libraryapi.controller.dto.AutorDTO;
import io.github.arthursilva.libraryapi.model.Autor;
import io.github.arthursilva.libraryapi.service.AutorService;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("autores")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autorDTO) {
        Autor autorEntidade = autorDTO.mapearParaAutor();
        service.salvar(autorEntidade);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorEntidade.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isPresent()) {
            Autor autorEntidade = autorOptional.get();
            AutorDTO autorDTO = new AutorDTO(autorEntidade.getId(), autorEntidade.getNome(), autorEntidade.getDataNascimento(), autorEntidade.getNacionalidade());
            return ResponseEntity.ok(autorDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {
        UUID idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletar(autorOptional.get());

        return ResponseEntity.noContent().build();
    }



}
