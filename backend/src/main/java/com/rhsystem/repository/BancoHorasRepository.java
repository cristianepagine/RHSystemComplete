package com.rhsystem.repository;

import com.rhsystem.model.BancoHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BancoHorasRepository extends JpaRepository<BancoHoras, Long> {
    List<BancoHoras> findByFuncionarioId(Long funcionarioId);
    List<BancoHoras> findByFuncionarioIdAndDataBetween(Long funcionarioId, LocalDate inicio, LocalDate fim);
    List<BancoHoras> findByFuncionarioIdAndAprovado(Long funcionarioId, Boolean aprovado);

    @Query("SELECT SUM(b.saldo) FROM BancoHoras b WHERE b.funcionario.id = :funcionarioId AND b.aprovado = true")
    java.math.BigDecimal calcularSaldoTotal(Long funcionarioId);
}
