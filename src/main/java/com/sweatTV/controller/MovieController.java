package com.sweatTV.controller;

import com.sweatTV.dto.MovieDTO;
import com.sweatTV.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> uploadMovie(@RequestBody MovieDTO movieDTO){
        return new ResponseEntity<>(movieService.uploadMovie(movieDTO), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO){
        return ResponseEntity.ok(movieService.updateMovie(id, movieDTO));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }


}
