package com.rhsystem.repository;

import com.rhsystem.model.StatusInscricao;
import com.rhsystem.model.TreinamentoInscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreinamentoInscricaoRepository extends JpaRepository<TreinamentoInscricao, Long> {
    List<TreinamentoInscricao> findByFuncionarioId(Long funcionarioId);
    List<TreinamentoInscricao> findByTreinamentoId(Long treinamentoId);
    List<TreinamentoInscricao> findByStatus(StatusInscricao status);
    List<TreinamentoInscricao> findByFuncionarioIdAndStatus(Long funcionarioId, StatusInscricao status);

    @Query("SELECT COUNT(i) FROM TreinamentoInscricao i WHERE i.treinamento.id = :treinamentoId AND i.status = 'APROVADA'")
    Long countInscricoesAprovadasPorTreinamento(Long treinamentoId);

    @Query("SELECT COUNT(i) FROM TreinamentoInscricao i WHERE i.status = 'PENDENTE'")
    Long countInscricoesPendentes();
}
