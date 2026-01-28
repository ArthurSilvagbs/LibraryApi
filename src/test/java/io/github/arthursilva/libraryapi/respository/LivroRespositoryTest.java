package io.github.arthursilva.libraryapi.respository;

import io.github.arthursilva.libraryapi.model.Autor;
import io.github.arthursilva.libraryapi.model.GeneroLivro;
import io.github.arthursilva.libraryapi.model.Livro;
import io.github.arthursilva.libraryapi.repository.AutorRepository;
import io.github.arthursilva.libraryapi.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class LivroRespositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setTitulo("Duna");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository.findById(UUID.fromString("bec92156-50cd-455d-8029-a10cc2b9129d")).orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setTitulo("UFO");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Maria ");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1950, 1, 31));

        livro.setAutor(autor);

        repository.save(livro);

    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("5ee19885-7261-4f88-9126-2625c232b0aa");
        Optional<Livro> possivelLivro = repository.findById(id);

        if (possivelLivro.isPresent()) {
            Livro livroEcontrado = possivelLivro.get();
            System.out.println("Dados do autor: ");
            System.out.println(possivelLivro.get());

            livroEcontrado.setDataPublicacao(LocalDate.of(1960, 1, 28));

            repository.save(livroEcontrado);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    @Test
    void atualizarAutorLivro() {
        var livroParaAtualizar = repository.findById(UUID.fromString("68a5f294-2171-4bbe-b7ad-015dcb7d23d0")).orElse(null);

        var autor = autorRepository.findById(UUID.fromString("72614ca0-6826-42e8-b084-5e37d951371f")).orElse(null);
        livroParaAtualizar.setAutor(autor);

        repository.save(livroParaAtualizar);

    }

    @Test
    public void listarTest() {
        List<Livro> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de lirvros: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("9b5fa089-acbd-4dbb-9337-afc59744195e");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("1697b8c8-4b46-4c8f-8471-6551a564ab32");
        var livro = repository.findById(id).get();
        repository.delete(livro);
    }


}
