package com.rhsystem.service;

import com.rhsystem.dto.DashboardDTO;
import com.rhsystem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final FuncionarioRepository funcionarioRepository;
    private final DepartamentoRepository departamentoRepository;
    private final CargoRepository cargoRepository;
    private final FeriasRepository feriasRepository;
    private final RegistroPontoRepository registroPontoRepository;

    @Transactional(readOnly = true)
    public DashboardDTO getDashboardData() {
        DashboardDTO dashboard = new DashboardDTO();

        dashboard.setTotalFuncionarios(funcionarioRepository.count());
        dashboard.setFuncionariosAtivos(funcionarioRepository.countAtivos());
        dashboard.setFuncionariosInativos(funcionarioRepository.countInativos());
        dashboard.setTotalDepartamentos(departamentoRepository.count());
        dashboard.setTotalCargos(cargoRepository.count());
        dashboard.setFeriasPendentes(feriasRepository.countPendentes());
        dashboard.setFeriasAprovadas(feriasRepository.countAprovadas());
        dashboard.setRegistrosPontoHoje(registroPontoRepository.countRegistrosHoje());

        return dashboard;
    }
}
