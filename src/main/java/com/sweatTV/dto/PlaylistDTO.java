package com.sweatTV.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class PlaylistDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    private Long ownerId;

    private Set<Long> movieId;

    private Set<Long> channelIds;
}
