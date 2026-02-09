package io.github.arthursilva.libraryapi.service;

import io.github.arthursilva.libraryapi.model.Livro;
import io.github.arthursilva.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;


    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }
}
