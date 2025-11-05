package com.rhsystem.repository;

import com.rhsystem.model.Avaliacao;
import com.rhsystem.model.StatusAvaliacao;
import com.rhsystem.model.TipoAvaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByFuncionarioId(Long funcionarioId);
    List<Avaliacao> findByAvaliadorId(Long avaliadorId);
    List<Avaliacao> findByStatus(StatusAvaliacao status);
    List<Avaliacao> findByTipo(TipoAvaliacao tipo);
    List<Avaliacao> findByFuncionarioIdAndStatus(Long funcionarioId, StatusAvaliacao status);

    @Query("SELECT AVG(a.notaFinal) FROM Avaliacao a WHERE a.funcionario.id = :funcionarioId AND a.status = 'CONCLUIDA'")
    Double calcularMediaAvaliacoes(Long funcionarioId);
}
