package com.rhsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "ferias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ferias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Funcionário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @NotNull(message = "Data de início é obrigatória")
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @NotNull(message = "Data de fim é obrigatória")
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusFerias status = StatusFerias.PENDENTE;

    @NotNull(message = "Dias solicitados é obrigatório")
    @Positive(message = "Dias solicitados deve ser positivo")
    @Column(name = "dias_solicitados", nullable = false)
    private Integer diasSolicitados;

    @Column(name = "observacao", length = 500)
    private String observacao;

    @Transient
    public Long calcularDiasCorridos() {
        if (dataInicio == null || dataFim == null) return null;
        return ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;
    }
}
