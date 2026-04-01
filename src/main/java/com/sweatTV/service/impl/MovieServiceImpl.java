package com.sweatTV.service.impl;

import com.sweatTV.dto.MovieDTO;
import com.sweatTV.entity.Movie;
import com.sweatTV.mapper.MovieMapper;
import com.sweatTV.repository.MovieRepository;
import com.sweatTV.service.MovieService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public MovieDTO uploadMovie(MovieDTO movieDTO) {
        log.info("Attempting to upload movie: {}", movieDTO.getTitle());
        //Map Dto to entity
        Movie movie = movieMapper.toEntity(movieDTO);

        log.info("Movie entity before save: title={}, videoUrl={}", movie.getTitle(), movie.getVideoUrl());
        //saved in database
        Movie savedMovie = movieRepository.save(movie);
        log.info("Movie saved successfully with id : {}", savedMovie.getId());

        //Map entity to Dto
        return movieMapper.toDto(savedMovie);
    }

    @Override
    public MovieDTO getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(()-> new RuntimeException("Movie not found"));
        return movieMapper.toDto(movie);
    }

    @Override
    @Transactional
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Movie not found"));

        movieMapper.updateMovieFromDto(movieDTO, movie);

        Movie updateMovie = movieRepository.save(movie);

        return movieMapper.toDto(updateMovie);
    }

    @Override
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)){
            throw new RuntimeException("Movie not found");
        }
        movieRepository.deleteById(id);

    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return List.of();
    }
}
