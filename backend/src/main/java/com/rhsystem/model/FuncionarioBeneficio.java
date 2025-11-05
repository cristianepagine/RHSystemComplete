package com.rhsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "funcionario_beneficios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioBeneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Funcionário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @NotNull(message = "Benefício é obrigatório")
    @ManyToOne
    @JoinColumn(name = "beneficio_id", nullable = false)
    private Beneficio beneficio;

    @NotNull(message = "Data de adesão é obrigatória")
    @Column(name = "data_adesao", nullable = false)
    private LocalDate dataAdesao;

    @Column(name = "data_cancelamento")
    private LocalDate dataCancelamento;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(length = 500)
    private String observacoes;
}
