package io.github.arthursilva.libraryapi.respository;

import io.github.arthursilva.libraryapi.model.Autor;
import io.github.arthursilva.libraryapi.model.GeneroLivro;
import io.github.arthursilva.libraryapi.model.Livro;
import io.github.arthursilva.libraryapi.repository.AutorRepository;
import io.github.arthursilva.libraryapi.repository.LivroRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRespositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1950, 1, 31));

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
    public void deletePorIdTest() {
        var id = UUID.fromString("d3c65fe7-f96c-471b-9bfe-4d0b2e07e9a1");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("5e38f149-7626-46bd-9fb6-7540f0fda116");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1970, 8, 5));

        Livro livro1 = new Livro();
        livro1.setIsbn("20847-84874");
        livro1.setTitulo("O roubo da casa assombrada");
        livro1.setPreco(BigDecimal.valueOf(204));
        livro1.setGenero(GeneroLivro.MISTERIO);
        livro1.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro1.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("20227-84354");
        livro2.setTitulo("Trono de Vidro");
        livro2.setPreco(BigDecimal.valueOf(150));
        livro2.setGenero(GeneroLivro.ROMANCE);
        livro2.setDataPublicacao(LocalDate.of(2004, 5, 22));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro1);
        autor.getLivros().add(livro2);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());

    }

    @Test
    void salvarAutorComLivrosComCascade() {

        Autor autor = new Autor();
        autor.setNome("Jr Martin");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1960, 10, 27));

        Livro livro1 = new Livro();
        livro1.setIsbn("12867-34594");
        livro1.setTitulo("Os Ventos do inverno");
        livro1.setPreco(BigDecimal.valueOf(109.99));
        livro1.setGenero(GeneroLivro.FICCAO);
        livro1.setDataPublicacao(LocalDate.of(2006, 7, 26));
        livro1.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("90627-82394");
        livro2.setTitulo("Fogo e Sangue");
        livro2.setPreco(BigDecimal.valueOf(130.50));
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setDataPublicacao(LocalDate.of(2008, 5, 30));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro1);
        autor.getLivros().add(livro2);

        repository.save(autor);

    }

    @Test
    @Transactional
    void listarLivrosAutor() {
        var id = UUID.fromString("2d9357ab-729a-4c10-af7d-0c2c02ddef39");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }


}
