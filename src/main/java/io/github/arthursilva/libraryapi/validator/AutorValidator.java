package io.github.arthursilva.libraryapi.validator;

import io.github.arthursilva.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.arthursilva.libraryapi.model.Autor;
import io.github.arthursilva.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository repository;

    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }

    public void validar(Autor autor) {
        if (existeAutorCadastrado(autor)) {
            throw new RegistroDuplicadoException("Autor já cadastrado!");
        }
    }

    private boolean existeAutorCadastrado(Autor autor) {
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()
        );

        if (autorEncontrado.isEmpty()) {
            return false;
        }

        if (autor.getId() == null) {
            return true;
        }

        // Se achou alguém E o ID é diferente do que estou editando, então é um duplicado real
        return !autor.getId().equals(autorEncontrado.get().getId());
    }

}
