package io.github.arthursilva.libraryapi.service;

import io.github.arthursilva.libraryapi.model.Autor;
import io.github.arthursilva.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;

    private AutorService(AutorRepository autorRepository) {
        this.repository = autorRepository;
    }

    public Autor salvar(Autor autor) {
        return repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

}
