package com.sweatTV.service;

import com.sweatTV.dto.PlaylistDTO;

import java.util.List;

public interface PlaylistService {

    PlaylistDTO createPlaylist(String email, String username);

    void addMovieToPlaylist(Long playlistId, Long movieId, String username);

    void updatePlaylistName(Long playlistId, String newName, String username);

    void removeMovieFromPlaylist(Long playlistId, Long movieId, String username);

    void deletePlaylist(Long playlistId, String username);

    PlaylistDTO getPlaylistById(Long playlistId, String username);

    List<PlaylistDTO> getAllPlaylistsForUser(String email);


}
