package com.sweatTV.service;

import com.sweatTV.dto.PlaylistDTO;

public interface PlaylistService {

    PlaylistDTO createPlaylist(String name, String username);

    void addMovieToPlaylist(Long playlistId, Long movieId, String username);

    void updatePlaylistName(Long playlistId, String newName, String username);

    void removeMovieFromPlaylist(Long playlistId, Long movieId, String username);

    void deletePlaylist(Long playlistId, String username);


}
