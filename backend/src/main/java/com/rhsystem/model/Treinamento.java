package com.rhsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "treinamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treinamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @Column(length = 2000)
    private String descricao;

    @Column(length = 500)
    private String instrutor;

    @NotNull(message = "Data de início é obrigatória")
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @NotNull(message = "Data de fim é obrigatória")
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Positive(message = "Carga horária deve ser positiva")
    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @Column(name = "vagas_disponiveis")
    private Integer vagasDisponiveis;

    @Column(precision = 10, scale = 2)
    private BigDecimal custo;

    @Column(length = 500)
    private String local;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(nullable = false)
    private Boolean obrigatorio = false;
}
