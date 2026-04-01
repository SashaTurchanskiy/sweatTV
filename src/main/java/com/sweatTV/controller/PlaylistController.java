package com.sweatTV.controller;

import com.sweatTV.dto.PlaylistDTO;
import com.sweatTV.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistDTO playlistDTO, Principal principal){
        return new ResponseEntity<>
                (
                playlistService.createPlaylist(playlistDTO.getName(), principal.getName()), HttpStatus.CREATED
    );
    }
    @PostMapping("/{playlistId}/add-movie/{movieId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> addMovieToPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long movieId,
            Principal principal){
        playlistService.addMovieToPlaylist(playlistId, movieId, principal.getName());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{playlistId}/movie/{movieId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> removeMovieFromPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long movieId,
            Principal principal){
        playlistService.removeMovieFromPlaylist(playlistId, movieId, principal.getName());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{playlistId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long playlistId, Principal principal){
        playlistService.deletePlaylist(playlistId, principal.getName());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylists(Principal principal){
       return ResponseEntity.ok(playlistService.getAllPlaylistsForUser(principal.getName()));
    }
}
