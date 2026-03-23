package com.sweatTV.mapper;

import com.sweatTV.dto.PlaylistDTO;
import com.sweatTV.entity.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    @Mapping(target = "ownerId", source = "owner.id")
   PlaylistDTO toDto(Playlist playlist);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "movies", ignore = true)
    Playlist toEntity(PlaylistDTO playlistDTO);

   /* List<PlaylistDTO> toDtoList(List<Playlist> playlists);
    //List<Playlist> toEntityList(List<PlaylistDTO> dtos);*/
}
