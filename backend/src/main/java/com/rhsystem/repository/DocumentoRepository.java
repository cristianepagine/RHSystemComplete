package com.rhsystem.repository;

import com.rhsystem.model.Documento;
import com.rhsystem.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    List<Documento> findByFuncionarioId(Long funcionarioId);
    List<Documento> findByFuncionarioIdAndAtivo(Long funcionarioId, Boolean ativo);
    List<Documento> findByTipo(TipoDocumento tipo);

    @Query("SELECT d FROM Documento d WHERE d.dataValidade IS NOT NULL AND d.dataValidade BETWEEN :inicio AND :fim AND d.ativo = true")
    List<Documento> findDocumentosVencendoEntre(LocalDate inicio, LocalDate fim);

    @Query("SELECT d FROM Documento d WHERE d.dataValidade IS NOT NULL AND d.dataValidade < :data AND d.ativo = true")
    List<Documento> findDocumentosVencidos(LocalDate data);
}
