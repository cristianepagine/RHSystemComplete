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
@Table(name = "avaliacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Funcionário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "avaliador_id")
    private Funcionario avaliador;

    @NotNull(message = "Tipo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAvaliacao tipo;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAvaliacao status = StatusAvaliacao.PENDENTE;

    @NotNull(message = "Período de início é obrigatório")
    @Column(name = "periodo_inicio", nullable = false)
    private LocalDate periodoInicio;

    @NotNull(message = "Período de fim é obrigatório")
    @Column(name = "periodo_fim", nullable = false)
    private LocalDate periodoFim;

    @Min(0)
    @Max(10)
    @Column(name = "nota_produtividade")
    private Integer notaProdutividade;

    @Min(0)
    @Max(10)
    @Column(name = "nota_qualidade")
    private Integer notaQualidade;

    @Min(0)
    @Max(10)
    @Column(name = "nota_comportamento")
    private Integer notaComportamento;

    @Min(0)
    @Max(10)
    @Column(name = "nota_iniciativa")
    private Integer notaIniciativa;

    @Min(0)
    @Max(10)
    @Column(name = "nota_trabalho_equipe")
    private Integer notaTrabalhoEquipe;

    @Column(name = "nota_final", precision = 4, scale = 2)
    private Double notaFinal;

    @Column(length = 2000)
    private String comentarios;

    @Column(length = 2000)
    private String pontosFortess;

    @Column(length = 2000)
    private String pontosDesenvolver;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @Column(name = "data_conclusao")
    private LocalDate dataConclusao;
}
