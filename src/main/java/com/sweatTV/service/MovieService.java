package com.sweatTV.service;

import com.sweatTV.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO uploadMovie(MovieDTO movieDTO);

    MovieDTO getMovieById(Long movieId);

    MovieDTO updateMovie(Long id, MovieDTO movieDTO);

    void deleteMovie(Long id);

    List<MovieDTO> getAllMovies();

}
