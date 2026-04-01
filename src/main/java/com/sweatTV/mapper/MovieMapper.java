package com.sweatTV.mapper;

import com.sweatTV.dto.MovieDTO;
import com.sweatTV.entity.Movie;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring") // Обов'язково для Spring Injection
public interface MovieMapper {

    // Мапимо Entity в DTO (для GET запитів)
    MovieDTO toDto(Movie movie);

    // Мапимо DTO в Entity (для POST запитів)
    @Mapping(target = "id", ignore = true) // Ігноруємо ID при створенні, щоб Hibernate сам його згенерував
    @Mapping(target = "createdAt", ignore = true) // Ігноруємо дату створення, вона сетається автоматично в Entity
    Movie toEntity(MovieDTO movieDTO);

    // Метод для оновлення існуючого об'єкта (PUT запити)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovieFromDto(MovieDTO movieDTO, @MappingTarget Movie movie);
}
