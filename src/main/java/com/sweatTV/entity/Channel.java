package com.sweatTV.entity;

import com.sweatTV.entity.enums.ChannelCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "channels")
@AllArgsConstructor
@NoArgsConstructor
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Channel name cannot be blank")
    @Column(nullable = false, unique = true)
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "Stream URL cannot be blank")
    private String streamUrl;

    @Enumerated(EnumType.STRING)
    private ChannelCategory category;

    private LocalDateTime startTime;
}
