package com.sweatTV.mapper;

import com.sweatTV.dto.MovieDTO;
import com.sweatTV.entity.Movie;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO toDto(Movie movie);
    Movie toEntity(MovieDTO movieDTO);

    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    void updateMovieFromDto(MovieDTO movieDTO, @MappingTarget Movie movie);

    /*List<MovieDTO> toDtoList(List<Movie> movies);
    List<Movie> toEntity(List<MovieDTO> dtos);*/
}
