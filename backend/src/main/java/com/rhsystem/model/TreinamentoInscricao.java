package com.rhsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "treinamento_inscricoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinamentoInscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Funcionário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @NotNull(message = "Treinamento é obrigatório")
    @ManyToOne
    @JoinColumn(name = "treinamento_id", nullable = false)
    private Treinamento treinamento;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusInscricao status = StatusInscricao.PENDENTE;

    @Column(name = "data_inscricao", nullable = false)
    private LocalDate dataInscricao = LocalDate.now();

    @Column(name = "data_aprovacao")
    private LocalDate dataAprovacao;

    @Column(name = "aprovado_por_id")
    private Long aprovadoPorId;

    @Column(name = "presente")
    private Boolean presente;

    @Min(0)
    @Max(10)
    @Column(name = "nota_avaliacao")
    private Integer notaAvaliacao;

    @Column(name = "certificado_url")
    private String certificadoUrl;

    @Column(length = 500)
    private String observacoes;
}
