package com.sweatTV.mapper;

import com.sweatTV.dto.ChannelDTO;
import com.sweatTV.entity.Channel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChannelMapper {

    ChannelDTO toDto(Channel channel);
    Channel toEntity(ChannelDTO channelDTO);

    List<ChannelDTO> toDtoList(List<Channel> channels);
}
