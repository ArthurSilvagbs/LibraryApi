package io.github.arthursilva.libraryapi.controller.mappers;

import io.github.arthursilva.libraryapi.controller.dto.AutorDTO;
import io.github.arthursilva.libraryapi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);
    AutorDTO toDTO(Autor autor);
}
