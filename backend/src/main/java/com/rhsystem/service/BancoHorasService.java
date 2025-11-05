package com.rhsystem.service;

import com.rhsystem.model.BancoHoras;
import com.rhsystem.repository.BancoHorasRepository;
import com.rhsystem.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BancoHorasService {

    private final BancoHorasRepository bancoHorasRepository;

    @Transactional(readOnly = true)
    public List<BancoHoras> findAll() {
        return bancoHorasRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BancoHoras findById(Long id) {
        return bancoHorasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de banco de horas não encontrado com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<BancoHoras> findByFuncionarioId(Long funcionarioId) {
        return bancoHorasRepository.findByFuncionarioId(funcionarioId);
    }

    @Transactional(readOnly = true)
    public BigDecimal calcularSaldoTotal(Long funcionarioId) {
        BigDecimal saldo = bancoHorasRepository.calcularSaldoTotal(funcionarioId);
        return saldo != null ? saldo : BigDecimal.ZERO;
    }

    @Transactional
    public BancoHoras create(BancoHoras bancoHoras) {
        calcularSaldo(bancoHoras);
        return bancoHorasRepository.save(bancoHoras);
    }

    @Transactional
    public BancoHoras aprovar(Long id, Long aprovadorId) {
        BancoHoras bancoHoras = findById(id);
        bancoHoras.setAprovado(true);
        bancoHoras.setDataAprovacao(LocalDate.now());
        bancoHoras.setAprovadoPorId(aprovadorId);
        return bancoHorasRepository.save(bancoHoras);
    }

    private void calcularSaldo(BancoHoras bancoHoras) {
        BigDecimal saldo = bancoHoras.getHorasExtras().subtract(bancoHoras.getHorasDevidas());
        bancoHoras.setSaldo(saldo);
    }
}
