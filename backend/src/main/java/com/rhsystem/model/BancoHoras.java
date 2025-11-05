package com.rhsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "banco_horas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BancoHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Funcionário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @NotNull(message = "Data é obrigatória")
    @Column(nullable = false)
    private LocalDate data;

    @Column(name = "horas_trabalhadas", precision = 5, scale = 2)
    private BigDecimal horasTrabalhadas = BigDecimal.ZERO;

    @Column(name = "horas_extras", precision = 5, scale = 2)
    private BigDecimal horasExtras = BigDecimal.ZERO;

    @Column(name = "horas_devidas", precision = 5, scale = 2)
    private BigDecimal horasDevidas = BigDecimal.ZERO;

    @Column(name = "saldo", precision = 5, scale = 2)
    private BigDecimal saldo = BigDecimal.ZERO;

    @Column(length = 500)
    private String observacao;

    @Column(nullable = false)
    private Boolean aprovado = false;

    @Column(name = "data_aprovacao")
    private LocalDate dataAprovacao;

    @Column(name = "aprovado_por_id")
    private Long aprovadoPorId;
}
