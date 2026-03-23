package com.sweatTV.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovieDTO {

    private String title;
    private String description;
    private String videoUrl;
    private String coverUrl;
    private Integer releaseYear;
    private Integer durationMinutes;
}
