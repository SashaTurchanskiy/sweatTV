package com.sweatTV.mapper;

import com.sweatTV.dto.MovieDTO;
import com.sweatTV.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO toDto(Movie movie);
    Movie toEntity(MovieDTO movieDTO);

    List<MovieDTO> toDtoList(List<Movie> movies);
    List<Movie> toEntity(List<MovieDTO> dtos);
}
