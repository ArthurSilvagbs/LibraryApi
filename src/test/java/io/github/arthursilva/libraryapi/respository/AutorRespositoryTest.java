package io.github.arthursilva.libraryapi.respository;

import io.github.arthursilva.libraryapi.model.Autor;
import io.github.arthursilva.libraryapi.repository.AutorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRespositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 2, 28));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("5ee19885-7261-4f88-9126-2625c232b0aa");
        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEcontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println(possivelAutor.get());

            autorEcontrado.setDataNascimento(LocalDate.of(1960, 1, 28));

            repository.save(autorEcontrado);
        } else {
            System.out.println("Autor não encontrado!");
        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("5ee19885-7261-4f88-9126-2625c232b0aa");
        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEcontrado = possivelAutor.get();
            repository.delete(autorEcontrado);
            System.out.println("Autor " + autorEcontrado + " deletado!");
        } else {
            System.out.println("Autor não encontrado!");
        }
    }

}
