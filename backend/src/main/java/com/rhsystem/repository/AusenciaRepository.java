package com.rhsystem.repository;

import com.rhsystem.model.Ausencia;
import com.rhsystem.model.TipoAusencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AusenciaRepository extends JpaRepository<Ausencia, Long> {
    List<Ausencia> findByFuncionarioId(Long funcionarioId);
    List<Ausencia> findByFuncionarioIdAndAprovado(Long funcionarioId, Boolean aprovado);
    List<Ausencia> findByTipo(TipoAusencia tipo);
    List<Ausencia> findByDataInicioBetween(LocalDate inicio, LocalDate fim);

    @Query("SELECT SUM(a.dias) FROM Ausencia a WHERE a.funcionario.id = :funcionarioId AND a.aprovado = true AND a.dataInicio >= :inicio AND a.dataFim <= :fim")
    Integer calcularTotalDiasAusencia(Long funcionarioId, LocalDate inicio, LocalDate fim);

    @Query("SELECT COUNT(a) FROM Ausencia a WHERE a.aprovado = false")
    Long countPendentes();
}
