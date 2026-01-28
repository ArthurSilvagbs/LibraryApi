package io.github.arthursilva.libraryapi;

import io.github.arthursilva.libraryapi.model.Autor;
import io.github.arthursilva.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
        AutorRepository repository = context.getBean(AutorRepository.class);
        exemploSalvarRegisto(repository);
	}

    public static void exemploSalvarRegisto(AutorRepository autorRepository) {
        Autor autor = new Autor();
        autor.setNome("José");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1950, 1, 31));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }
}
