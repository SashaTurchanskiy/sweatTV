package com.sweatTV.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PlaylistDTO {
    private Long id;
    private String name;
    private Long ownerId;
    private List<MovieDTO> movies;
}
