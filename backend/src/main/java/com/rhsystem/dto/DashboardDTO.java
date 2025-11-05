package com.rhsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    // Funcionários
    private Long totalFuncionarios;
    private Long funcionariosAtivos;
    private Long funcionariosInativos;

    // Organização
    private Long totalDepartamentos;
    private Long totalCargos;

    // Férias e Ausências
    private Long feriasPendentes;
    private Long feriasAprovadas;
    private Long ausenciasPendentes;

    // Ponto
    private Long registrosPontoHoje;

    // Recrutamento
    private Long vagasAbertas;
    private Long novasCandidaturas;

    // Treinamento
    private Long inscricoesTreinamentoPendentes;

    // Onboarding
    private Long onboardingsEmAndamento;

    // Folha de Pagamento
    private Long folhasPendentes;
}
