package com.rhsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "onboarding")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Onboarding {

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

    @Column(name = "data_previsao_conclusao")
    private LocalDate dataPrevisaoConclusao;

    @Column(name = "data_conclusao")
    private LocalDate dataConclusao;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOnboarding status = StatusOnboarding.PENDENTE;

    @Column(name = "responsavel_id")
    private Long responsavelId;

    @Column(length = 1000)
    private String observacoes;

    @Column(name = "progresso_percentual")
    private Integer progressoPercentual = 0;
}
