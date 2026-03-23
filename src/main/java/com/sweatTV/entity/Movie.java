package com.sweatTV.entity;

import com.sweatTV.entity.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT") // Дозволяє довгий опис
    private String description;

    @Column(nullable = false)
    private String videoUrl; // Посилання на файл фільму

    private String coverUrl; // Посилання на картинку (постер)

    private Integer releaseYear; // Рік випуску

    private Integer durationMinutes; // Тривалість

    // Дата додавання на сайт (корисно для сортування "Новинки")
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


    
}
