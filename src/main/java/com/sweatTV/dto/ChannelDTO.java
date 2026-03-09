package com.sweatTV.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChannelDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "StreamUrl cannot be empty")
    private String streamUrl;

    private String category;

    private LocalDateTime startTime;
}
