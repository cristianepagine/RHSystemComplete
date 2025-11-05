package com.rhsystem.repository;

import com.rhsystem.model.FolhaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FolhaPagamentoRepository extends JpaRepository<FolhaPagamento, Long> {
    List<FolhaPagamento> findByFuncionarioId(Long funcionarioId);
    List<FolhaPagamento> findByMesAno(LocalDate mesAno);
    Optional<FolhaPagamento> findByFuncionarioIdAndMesAno(Long funcionarioId, LocalDate mesAno);
    List<FolhaPagamento> findByPago(Boolean pago);

    @Query("SELECT SUM(f.salarioLiquido) FROM FolhaPagamento f WHERE f.mesAno = :mesAno")
    java.math.BigDecimal calcularTotalFolha(LocalDate mesAno);

    @Query("SELECT COUNT(f) FROM FolhaPagamento f WHERE f.mesAno = :mesAno AND f.pago = false")
    Long countPendentesPorMes(LocalDate mesAno);
}
