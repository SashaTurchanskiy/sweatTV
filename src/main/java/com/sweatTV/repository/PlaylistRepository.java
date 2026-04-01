package com.sweatTV.repository;

import com.sweatTV.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    List<Playlist> findByOwnerId(Long ownerId);

    List<Playlist> findByOwnerEmail(String email);
}
