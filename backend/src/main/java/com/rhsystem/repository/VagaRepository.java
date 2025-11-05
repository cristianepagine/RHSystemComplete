package com.rhsystem.repository;

import com.rhsystem.model.StatusVaga;
import com.rhsystem.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByStatus(StatusVaga status);
    List<Vaga> findByPublicada(Boolean publicada);
    List<Vaga> findByStatusAndPublicada(StatusVaga status, Boolean publicada);
    List<Vaga> findByDepartamentoId(Long departamentoId);
    List<Vaga> findByCargoId(Long cargoId);

    @Query("SELECT COUNT(v) FROM Vaga v WHERE v.status = 'ABERTA' AND v.publicada = true")
    Long countVagasAbertas();
}
