package com.rhsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidaturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Candidato é obrigatório")
    @ManyToOne
    @JoinColumn(name = "candidato_id", nullable = false)
    private Candidato candidato;

    @NotNull(message = "Vaga é obrigatória")
    @ManyToOne
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCandidatura status = StatusCandidatura.NOVA;

    @Column(name = "data_candidatura", nullable = false)
    private LocalDateTime dataCandidatura = LocalDateTime.now();

    @Column(name = "data_entrevista")
    private LocalDateTime dataEntrevista;

    @Column(name = "nota_entrevista")
    private Integer notaEntrevista;

    @Column(name = "nota_teste")
    private Integer notaTeste;

    @Column(length = 2000)
    private String observacoes;

    @Column(name = "avaliado_por_id")
    private Long avaliadoPorId;

    @Column(name = "pretensao_salarial", precision = 10, scale = 2)
    private java.math.BigDecimal pretensaoSalarial;
}
