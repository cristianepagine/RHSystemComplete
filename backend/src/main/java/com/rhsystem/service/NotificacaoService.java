package com.rhsystem.service;

import com.rhsystem.model.Notificacao;
import com.rhsystem.model.Funcionario;
import com.rhsystem.repository.NotificacaoRepository;
import com.rhsystem.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    @Transactional(readOnly = true)
    public List<Notificacao> findAll() {
        return notificacaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Notificacao findById(Long id) {
        return notificacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Notificacao> findByFuncionarioId(Long funcionarioId) {
        return notificacaoRepository.findNotificacoesParaFuncionario(funcionarioId);
    }

    @Transactional(readOnly = true)
    public Long countNaoLidas(Long funcionarioId) {
        return notificacaoRepository.countNaoLidasPorFuncionario(funcionarioId);
    }

    @Transactional
    public Notificacao create(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    @Transactional
    public Notificacao marcarComoLida(Long id) {
        Notificacao notificacao = findById(id);
        notificacao.setLida(true);
        notificacao.setDataLeitura(LocalDateTime.now());
        return notificacaoRepository.save(notificacao);
    }

    @Transactional
    public void marcarTodasComoLidas(Long funcionarioId) {
        List<Notificacao> notificacoes = notificacaoRepository.findByFuncionarioIdAndLida(funcionarioId, false);
        notificacoes.forEach(n -> {
            n.setLida(true);
            n.setDataLeitura(LocalDateTime.now());
        });
        notificacaoRepository.saveAll(notificacoes);
    }
}
