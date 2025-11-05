package com.rhsystem.service;

import com.rhsystem.model.FolhaPagamento;
import com.rhsystem.model.Funcionario;
import com.rhsystem.repository.FolhaPagamentoRepository;
import com.rhsystem.repository.FuncionarioRepository;
import com.rhsystem.repository.FuncionarioBeneficioRepository;
import com.rhsystem.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolhaPagamentoService {

    private final FolhaPagamentoRepository folhaPagamentoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioBeneficioRepository funcionarioBeneficioRepository;

    @Transactional(readOnly = true)
    public List<FolhaPagamento> findAll() {
        return folhaPagamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public FolhaPagamento findById(Long id) {
        return folhaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Folha de pagamento não encontrada com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<FolhaPagamento> findByFuncionarioId(Long funcionarioId) {
        return folhaPagamentoRepository.findByFuncionarioId(funcionarioId);
    }

    @Transactional
    public FolhaPagamento gerarFolha(Long funcionarioId, LocalDate mesAno) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));

        FolhaPagamento folha = new FolhaPagamento();
        folha.setFuncionario(funcionario);
        folha.setMesAno(mesAno);
        folha.setSalarioBase(funcionario.getSalario());

        // Calcular proventos
        BigDecimal totalProventos = folha.getSalarioBase()
                .add(folha.getHorasExtras() != null ? folha.getHorasExtras() : BigDecimal.ZERO)
                .add(folha.getBonus() != null ? folha.getBonus() : BigDecimal.ZERO)
                .add(folha.getComissoes() != null ? folha.getComissoes() : BigDecimal.ZERO);
        folha.setTotalProventos(totalProventos);

        // Calcular descontos
        calcularINSS(folha);
        calcularIRRF(folha);
        calcularFGTS(folha);

        BigDecimal descontosBeneficios = funcionarioBeneficioRepository.calcularTotalDescontosBeneficios(funcionarioId);

        BigDecimal totalDescontos = folha.getInss()
                .add(folha.getIrrf())
                .add(descontosBeneficios != null ? descontosBeneficios : BigDecimal.ZERO);
        folha.setTotalDescontos(totalDescontos);

        // Calcular salário líquido
        BigDecimal salarioLiquido = totalProventos.subtract(totalDescontos);
        folha.setSalarioLiquido(salarioLiquido);

        return folhaPagamentoRepository.save(folha);
    }

    private void calcularINSS(FolhaPagamento folha) {
        BigDecimal salario = folha.getTotalProventos();
        BigDecimal inss;

        // Tabela INSS 2024 (simplificada)
        if (salario.compareTo(new BigDecimal("1320.00")) <= 0) {
            inss = salario.multiply(new BigDecimal("0.075"));
        } else if (salario.compareTo(new BigDecimal("2571.29")) <= 0) {
            inss = salario.multiply(new BigDecimal("0.09"));
        } else if (salario.compareTo(new BigDecimal("3856.94")) <= 0) {
            inss = salario.multiply(new BigDecimal("0.12"));
        } else if (salario.compareTo(new BigDecimal("7507.49")) <= 0) {
            inss = salario.multiply(new BigDecimal("0.14"));
        } else {
            inss = new BigDecimal("7507.49").multiply(new BigDecimal("0.14"));
        }

        folha.setInss(inss.setScale(2, RoundingMode.HALF_UP));
    }

    private void calcularIRRF(FolhaPagamento folha) {
        BigDecimal baseCalculo = folha.getTotalProventos().subtract(folha.getInss());
        BigDecimal irrf = BigDecimal.ZERO;

        // Tabela IRRF 2024 (simplificada)
        if (baseCalculo.compareTo(new BigDecimal("2112.00")) > 0) {
            if (baseCalculo.compareTo(new BigDecimal("2826.65")) <= 0) {
                irrf = baseCalculo.multiply(new BigDecimal("0.075")).subtract(new BigDecimal("158.40"));
            } else if (baseCalculo.compareTo(new BigDecimal("3751.05")) <= 0) {
                irrf = baseCalculo.multiply(new BigDecimal("0.15")).subtract(new BigDecimal("370.40"));
            } else if (baseCalculo.compareTo(new BigDecimal("4664.68")) <= 0) {
                irrf = baseCalculo.multiply(new BigDecimal("0.225")).subtract(new BigDecimal("651.73"));
            } else {
                irrf = baseCalculo.multiply(new BigDecimal("0.275")).subtract(new BigDecimal("884.96"));
            }
        }

        folha.setIrrf(irrf.max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP));
    }

    private void calcularFGTS(FolhaPagamento folha) {
        BigDecimal fgts = folha.getSalarioBase().multiply(new BigDecimal("0.08"));
        folha.setFgts(fgts.setScale(2, RoundingMode.HALF_UP));
    }

    @Transactional
    public FolhaPagamento marcarComoPago(Long id) {
        FolhaPagamento folha = findById(id);
        folha.setPago(true);
        folha.setDataPagamento(LocalDate.now());
        return folhaPagamentoRepository.save(folha);
    }
}
