package com.rhsystem.repository;

import com.rhsystem.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUsuarioId(Long usuarioId);
    List<AuditLog> findByEntidade(String entidade);
    List<AuditLog> findByEntidadeAndEntidadeId(String entidade, Long entidadeId);
    List<AuditLog> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT a FROM AuditLog a WHERE a.usuarioId = :usuarioId AND a.dataHora BETWEEN :inicio AND :fim")
    List<AuditLog> findByUsuarioAndPeriodo(Long usuarioId, LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT COUNT(a) FROM AuditLog a WHERE a.dataHora >= :data")
    Long countAcoesDesde(LocalDateTime data);
}
