package io.github.arthursilva.libraryapi.controller.dto;

import io.github.arthursilva.libraryapi.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PesquisaLivroDTO(String isbn, String titulo, LocalDate dataPublicacao, GeneroLivro genero, BigDecimal preco, AutorDTO autor) {
}
