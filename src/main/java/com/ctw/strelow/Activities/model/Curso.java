package com.ctw.strelow.Activities.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    private Professor professor;

}