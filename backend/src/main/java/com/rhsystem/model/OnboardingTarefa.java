package com.rhsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "onboarding_tarefas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingTarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Onboarding é obrigatório")
    @ManyToOne
    @JoinColumn(name = "onboarding_id", nullable = false)
    private Onboarding onboarding;

    @NotBlank(message = "Descrição é obrigatória")
    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(name = "responsavel_id")
    private Long responsavelId;

    @Column(name = "prazo")
    private LocalDate prazo;

    @Column(nullable = false)
    private Boolean concluida = false;

    @Column(name = "data_conclusao")
    private LocalDate dataConclusao;

    @Column(name = "ordem")
    private Integer ordem;

    @Column(length = 500)
    private String observacoes;
}
