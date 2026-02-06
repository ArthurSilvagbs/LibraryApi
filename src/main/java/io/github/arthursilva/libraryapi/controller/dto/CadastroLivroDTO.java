package io.github.arthursilva.libraryapi.controller.dto;

import io.github.arthursilva.libraryapi.model.Autor;
import io.github.arthursilva.libraryapi.model.GeneroLivro;
import io.github.arthursilva.libraryapi.model.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(

        @ISBN
        @NotBlank(message = "campo obrigatorio")
        String isbn,

        @NotBlank(message = "campo obrigatorio")
        String titulo,

        @NotNull(message = "campo obrigatorio")
        @Past(message = "nao pode ser uma data futura")
        LocalDate dataPublicacao,

        GeneroLivro genero,

        BigDecimal preco,

        @NotNull(message = "campo obrigatorio")
        UUID idAutor){

    public Livro mapearParaLivro() {
        return new Livro(this.isbn, this.titulo, this.dataPublicacao, this.genero, this.preco, this.idAutor);
    }

}
