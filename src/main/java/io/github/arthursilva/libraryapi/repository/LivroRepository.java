package io.github.arthursilva.libraryapi.repository;

import io.github.arthursilva.libraryapi.model.Autor;
import io.github.arthursilva.libraryapi.model.GeneroLivro;
import io.github.arthursilva.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //select * from livro where autor_id = id
    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    List<Livro> findByTituloLike();

    @Query("SELECT * FROM livro")
    List<Livro> listarTodos();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();

    @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro, @Param("paramOrdenacao") String nomePropriedade);
}
