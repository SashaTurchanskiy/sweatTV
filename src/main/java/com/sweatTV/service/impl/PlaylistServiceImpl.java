package com.sweatTV.service.impl;

import com.sweatTV.dto.PlaylistDTO;
import com.sweatTV.entity.Movie;
import com.sweatTV.entity.Playlist;
import com.sweatTV.entity.User;
import com.sweatTV.mapper.PlaylistMapper;
import com.sweatTV.repository.MovieRepository;
import com.sweatTV.repository.PlaylistRepository;
import com.sweatTV.repository.UserRepository;
import com.sweatTV.service.PlaylistService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;


    @Override
    public PlaylistDTO createPlaylist(String playlistName, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Playlist playlist = new Playlist();
        playlist.setName(playlistName);
        playlist.setOwner(user);

        Playlist savedPlaylist = playlistRepository.save(playlist);

        return playlistMapper.toDto(savedPlaylist);
    }

    @Override
    @Transactional
    public void addMovieToPlaylist(Long playlistId, Long movieId, String username) {
        Playlist playlist = getPlaylistAndCheckOwner(playlistId, username);

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(()-> new RuntimeException("Movie not found"));

        if (playlist.getMovies().contains(movie)){
            throw new RuntimeException("Movie already in playlist");
        }
        playlist.getMovies().add(movie);

        playlistRepository.save(playlist);
    }

    @Override
    @Transactional
    public void updatePlaylistName(Long playlistId, String newName, String username) {
        Playlist playlist = getPlaylistAndCheckOwner(playlistId, username);
        playlist.setName(newName);
        playlistRepository.save(playlist);
    }

    @Override
    @Transactional
    public void removeMovieFromPlaylist(Long playlistId, Long movieId, String username) {
        Playlist playlist = getPlaylistAndCheckOwner(playlistId, username);

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(()-> new RuntimeException("Movie not found"));

        boolean removed = playlist.getMovies().removeIf(m -> m.getId().equals(movieId));
        if (!removed){
            throw new RuntimeException("Movie not found in playlist");
        }
        playlistRepository.save(playlist);

    }

    @Override
    public void deletePlaylist(Long playlistId, String username) {
        Playlist playlist = getPlaylistAndCheckOwner(playlistId, username);
        playlistRepository.delete(playlist);

    }

    @Override
    public PlaylistDTO getPlaylistById(Long playlistId, String username) {
        Playlist playlist = getPlaylistAndCheckOwner(playlistId, username);
        return playlistMapper.toDto(playlist);
    }

    @Override
    public List<PlaylistDTO> getAllPlaylistsForUser(String email) {
        return playlistRepository.findByOwnerEmail(email).stream()
                .map(playlistMapper::toDto)
                .collect(Collectors.toList());
    }

    private Playlist getPlaylistAndCheckOwner(Long playlistId, String userEmail){
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(()-> new RuntimeException("Playlist not found"));

        if (!playlist.getOwner().getEmail().equals(userEmail)){
            throw new RuntimeException("Access denied: you are not the owner of this playlist");
        }
        return playlist;
    }
}
