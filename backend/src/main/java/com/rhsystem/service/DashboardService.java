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
    private final AusenciaRepository ausenciaRepository;
    private final VagaRepository vagaRepository;
    private final CandidaturaRepository candidaturaRepository;
    private final TreinamentoInscricaoRepository treinamentoInscricaoRepository;
    private final OnboardingRepository onboardingRepository;
    private final FolhaPagamentoRepository folhaPagamentoRepository;

    @Transactional(readOnly = true)
    public DashboardDTO getDashboardData() {
        DashboardDTO dashboard = new DashboardDTO();

        // Funcionários
        dashboard.setTotalFuncionarios(funcionarioRepository.count());
        dashboard.setFuncionariosAtivos(funcionarioRepository.countAtivos());
        dashboard.setFuncionariosInativos(funcionarioRepository.countInativos());

        // Organização
        dashboard.setTotalDepartamentos(departamentoRepository.count());
        dashboard.setTotalCargos(cargoRepository.count());

        // Férias e Ausências
        dashboard.setFeriasPendentes(feriasRepository.countPendentes());
        dashboard.setFeriasAprovadas(feriasRepository.countAprovadas());
        dashboard.setAusenciasPendentes(ausenciaRepository.countPendentes());

        // Ponto
        dashboard.setRegistrosPontoHoje(registroPontoRepository.countRegistrosHoje());

        // Recrutamento
        dashboard.setVagasAbertas(vagaRepository.countVagasAbertas());
        dashboard.setNovasCandidaturas(candidaturaRepository.countNovasCandidaturas());

        // Treinamento
        dashboard.setInscricoesTreinamentoPendentes(treinamentoInscricaoRepository.countInscricoesPendentes());

        // Onboarding
        dashboard.setOnboardingsEmAndamento(onboardingRepository.countEmAndamento());

        // Folha de Pagamento
        java.time.LocalDate mesAtual = java.time.LocalDate.now().withDayOfMonth(1);
        dashboard.setFolhasPendentes(folhaPagamentoRepository.countPendentesPorMes(mesAtual));

        return dashboard;
    }
}
