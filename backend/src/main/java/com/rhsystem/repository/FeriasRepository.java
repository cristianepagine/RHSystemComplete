package com.rhsystem.repository;

import com.rhsystem.model.Ferias;
import com.rhsystem.model.StatusFerias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeriasRepository extends JpaRepository<Ferias, Long> {
    List<Ferias> findByFuncionarioId(Long funcionarioId);
    List<Ferias> findByStatus(StatusFerias status);

    @Query("SELECT COUNT(f) FROM Ferias f WHERE f.status = 'PENDENTE'")
    Long countPendentes();

    @Query("SELECT COUNT(f) FROM Ferias f WHERE f.status = 'APROVADA'")
    Long countAprovadas();

    List<Ferias> findByFuncionarioIdAndStatus(Long funcionarioId, StatusFerias status);
}
