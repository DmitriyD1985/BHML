package ru.timber.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Songs")
@NamedQuery(name = "getAllSongs", query = "SELECT s FROM Songs s")
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "songName is required")
    private String songName;
}