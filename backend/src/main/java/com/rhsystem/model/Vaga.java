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
@Table(name = "vagas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Column(nullable = false)
    private String titulo;

    @Column(length = 2000)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @NotNull(message = "Número de vagas é obrigatório")
    @Positive(message = "Número de vagas deve ser positivo")
    @Column(name = "numero_vagas", nullable = false)
    private Integer numeroVagas;

    @Column(name = "salario_min", precision = 10, scale = 2)
    private BigDecimal salarioMin;

    @Column(name = "salario_max", precision = 10, scale = 2)
    private BigDecimal salarioMax;

    @Column(length = 1000)
    private String requisitos;

    @Column(length = 1000)
    private String beneficios;

    @NotNull(message = "Data de abertura é obrigatória")
    @Column(name = "data_abertura", nullable = false)
    private LocalDate dataAbertura = LocalDate.now();

    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVaga status = StatusVaga.ABERTA;

    @Column(name = "criado_por_id")
    private Long criadoPorId;

    @Column(nullable = false)
    private Boolean publicada = false;
}
