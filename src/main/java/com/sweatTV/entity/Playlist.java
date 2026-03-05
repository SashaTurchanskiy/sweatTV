package com.sweatTV.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists")
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Playlist name cannot be blank")
    private String name;

    //Playlist owner
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    //Movies in the playlist
    @ManyToMany
    @JoinTable(
            name = "playlist_movies",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies = new HashSet<>();

    //Channels in the playlist
    @ManyToMany
    @JoinTable(
            name = "playlist_channels",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id"))
    private Set<Channel> channels = new HashSet<>();
}
