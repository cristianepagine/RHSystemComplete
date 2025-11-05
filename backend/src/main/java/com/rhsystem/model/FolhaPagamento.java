package com.rhsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "folha_pagamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FolhaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Funcionário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @NotNull(message = "Mês/Ano é obrigatório")
    @Column(name = "mes_ano", nullable = false)
    private LocalDate mesAno;

    @NotNull(message = "Salário base é obrigatório")
    @Positive(message = "Salário base deve ser positivo")
    @Column(name = "salario_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal salarioBase;

    @Column(name = "horas_extras", precision = 10, scale = 2)
    private BigDecimal horasExtras = BigDecimal.ZERO;

    @Column(name = "adicional_noturno", precision = 10, scale = 2)
    private BigDecimal adicionalNoturno = BigDecimal.ZERO;

    @Column(name = "adicional_periculosidade", precision = 10, scale = 2)
    private BigDecimal adicionalPericulosidade = BigDecimal.ZERO;

    @Column(name = "adicional_insalubridade", precision = 10, scale = 2)
    private BigDecimal adicionalInsalubridade = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal bonus = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal comissoes = BigDecimal.ZERO;

    @Column(name = "total_proventos", precision = 10, scale = 2)
    private BigDecimal totalProventos;

    @Column(name = "inss", precision = 10, scale = 2)
    private BigDecimal inss = BigDecimal.ZERO;

    @Column(name = "irrf", precision = 10, scale = 2)
    private BigDecimal irrf = BigDecimal.ZERO;

    @Column(name = "fgts", precision = 10, scale = 2)
    private BigDecimal fgts = BigDecimal.ZERO;

    @Column(name = "vale_transporte", precision = 10, scale = 2)
    private BigDecimal valeTransporte = BigDecimal.ZERO;

    @Column(name = "vale_refeicao", precision = 10, scale = 2)
    private BigDecimal valeRefeicao = BigDecimal.ZERO;

    @Column(name = "plano_saude", precision = 10, scale = 2)
    private BigDecimal planoSaude = BigDecimal.ZERO;

    @Column(name = "outros_descontos", precision = 10, scale = 2)
    private BigDecimal outrosDescontos = BigDecimal.ZERO;

    @Column(name = "total_descontos", precision = 10, scale = 2)
    private BigDecimal totalDescontos;

    @Column(name = "salario_liquido", nullable = false, precision = 10, scale = 2)
    private BigDecimal salarioLiquido;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(nullable = false)
    private Boolean pago = false;

    @Column(name = "contracheque_url")
    private String contrachequeUrl;

    @Column(length = 500)
    private String observacoes;
}
