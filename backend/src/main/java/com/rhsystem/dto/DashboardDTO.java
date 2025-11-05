package com.rhsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    private Long totalFuncionarios;
    private Long funcionariosAtivos;
    private Long funcionariosInativos;
    private Long totalDepartamentos;
    private Long totalCargos;
    private Long feriasPendentes;
    private Long feriasAprovadas;
    private Long registrosPontoHoje;
}
