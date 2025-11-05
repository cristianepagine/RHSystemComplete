package com.rhsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "ausencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ausencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Funcionário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @NotNull(message = "Tipo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAusencia tipo;

    @NotNull(message = "Data de início é obrigatória")
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @NotNull(message = "Data de fim é obrigatória")
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(nullable = false)
    private Integer dias;

    @Column(length = 1000)
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "documento_id")
    private Documento documento;

    @Column(nullable = false)
    private Boolean aprovado = false;

    @Column(name = "data_aprovacao")
    private LocalDate dataAprovacao;

    @Column(name = "aprovado_por_id")
    private Long aprovadoPorId;

    @Column(name = "observacao_aprovacao", length = 500)
    private String observacaoAprovacao;
}
