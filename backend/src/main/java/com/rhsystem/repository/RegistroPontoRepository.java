package com.rhsystem.repository;

import com.rhsystem.model.RegistroPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {
    List<RegistroPonto> findByFuncionarioId(Long funcionarioId);

    List<RegistroPonto> findByFuncionarioIdAndDataHoraBetween(
        Long funcionarioId,
        LocalDateTime inicio,
        LocalDateTime fim
    );

    @Query("SELECT COUNT(r) FROM RegistroPonto r WHERE DATE(r.dataHora) = CURRENT_DATE")
    Long countRegistrosHoje();

    @Query("SELECT r FROM RegistroPonto r WHERE r.funcionarioId = :funcionarioId " +
           "AND DATE(r.dataHora) = CURRENT_DATE ORDER BY r.dataHora DESC")
    List<RegistroPonto> findRegistrosHojePorFuncionario(@Param("funcionarioId") Long funcionarioId);
}
