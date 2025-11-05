package com.rhsystem.repository;

import com.rhsystem.model.Notificacao;
import com.rhsystem.model.TipoNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByFuncionarioId(Long funcionarioId);
    List<Notificacao> findByFuncionarioIdAndLida(Long funcionarioId, Boolean lida);
    List<Notificacao> findByTipo(TipoNotificacao tipo);
    List<Notificacao> findByGlobal(Boolean global);

    @Query("SELECT COUNT(n) FROM Notificacao n WHERE n.funcionario.id = :funcionarioId AND n.lida = false")
    Long countNaoLidasPorFuncionario(Long funcionarioId);

    @Query("SELECT n FROM Notificacao n WHERE n.funcionario.id = :funcionarioId OR n.global = true ORDER BY n.dataCriacao DESC")
    List<Notificacao> findNotificacoesParaFuncionario(Long funcionarioId);
}
