package com.dharnish.updatecheck.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime detectedAt;

    @Column(length = 1000)
    private String oldHash;

    @Column(length = 1000)
    private String newHash;

    @ManyToOne
    @JoinColumn(name = "tracked_url_id")
    private TrackedUrl trackedUrl;
}