package io.github.arthursilva.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data
public class Livro {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", scale = 2, precision = 18)
    private BigDecimal preco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro() {
    }

    public Livro(String titulo, LocalDate dataPublicacao, GeneroLivro genero, BigDecimal preco, Autor autor) {
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
        this.genero = genero;
        this.preco = preco;
        this.autor = autor;
    }
}
