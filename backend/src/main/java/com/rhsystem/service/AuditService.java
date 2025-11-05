package com.rhsystem.service;

import com.rhsystem.model.AuditLog;
import com.rhsystem.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    @Transactional
    public void registrarAcao(Long usuarioId, String usuarioNome, String acao, String entidade,
                              Long entidadeId, String detalhes, String ipAddress, String userAgent) {
        AuditLog log = new AuditLog();
        log.setUsuarioId(usuarioId);
        log.setUsuarioNome(usuarioNome);
        log.setAcao(acao);
        log.setEntidade(entidade);
        log.setEntidadeId(entidadeId);
        log.setDetalhes(detalhes);
        log.setIpAddress(ipAddress);
        log.setUserAgent(userAgent);
        log.setDataHora(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    @Transactional(readOnly = true)
    public List<AuditLog> findAll() {
        return auditLogRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AuditLog> findByUsuarioId(Long usuarioId) {
        return auditLogRepository.findByUsuarioId(usuarioId);
    }

    @Transactional(readOnly = true)
    public List<AuditLog> findByEntidade(String entidade, Long entidadeId) {
        return auditLogRepository.findByEntidadeAndEntidadeId(entidade, entidadeId);
    }

    @Transactional(readOnly = true)
    public List<AuditLog> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return auditLogRepository.findByDataHoraBetween(inicio, fim);
    }
}
