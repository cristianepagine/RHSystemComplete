package com.rhsystem.repository;

import com.rhsystem.model.Treinamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TreinamentoRepository extends JpaRepository<Treinamento, Long> {
    List<Treinamento> findByAtivo(Boolean ativo);
    List<Treinamento> findByObrigatorio(Boolean obrigatorio);

    @Query("SELECT t FROM Treinamento t WHERE t.dataInicio >= :dataAtual AND t.ativo = true ORDER BY t.dataInicio")
    List<Treinamento> findTreinamentosFuturos(LocalDate dataAtual);

    @Query("SELECT t FROM Treinamento t WHERE t.dataInicio <= :dataAtual AND t.dataFim >= :dataAtual AND t.ativo = true")
    List<Treinamento> findTreinamentosEmAndamento(LocalDate dataAtual);
}
