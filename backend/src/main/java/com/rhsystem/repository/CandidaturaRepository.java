package com.rhsystem.repository;

import com.rhsystem.model.Candidatura;
import com.rhsystem.model.StatusCandidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
    List<Candidatura> findByCandidatoId(Long candidatoId);
    List<Candidatura> findByVagaId(Long vagaId);
    List<Candidatura> findByStatus(StatusCandidatura status);
    List<Candidatura> findByVagaIdAndStatus(Long vagaId, StatusCandidatura status);

    @Query("SELECT COUNT(c) FROM Candidatura c WHERE c.vaga.id = :vagaId")
    Long countCandidaturasPorVaga(Long vagaId);

    @Query("SELECT COUNT(c) FROM Candidatura c WHERE c.status = 'NOVA'")
    Long countNovasCandidaturas();
}
