package com.sweatTV.mapper;

import com.sweatTV.dto.PlaylistDTO;
import com.sweatTV.entity.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
     //@Mapping(target = "ownerId", source = "owner.id")
    //@Mapping(target = "movieIds", expression = "java(playlist.getMovies().stream().map(m -> m.getId()).collect(java.util.stream.Collectors.toSet()))")
    //@Mapping(target = "channelIds", expression = "java(playlist.getChannels().stream().map(c -> c.getId()).collect(java.util.stream.Collectors.toSet()))")
    PlaylistDTO toDto(Playlist playlist);

   /* @Mapping(target = "owner", ignore = true)
    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "channels", ignore = true)*/
    Playlist toEntity(PlaylistDTO playlistDTO);

    List<PlaylistDTO> toDtoList(List<Playlist> playlists);
    //List<Playlist> toEntityList(List<PlaylistDTO> dtos);
}
