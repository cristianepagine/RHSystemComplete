package com.rhsystem.repository;

import com.rhsystem.model.OnboardingTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnboardingTarefaRepository extends JpaRepository<OnboardingTarefa, Long> {
    List<OnboardingTarefa> findByOnboardingId(Long onboardingId);
    List<OnboardingTarefa> findByOnboardingIdAndConcluida(Long onboardingId, Boolean concluida);
    List<OnboardingTarefa> findByResponsavelId(Long responsavelId);

    @Query("SELECT COUNT(t) FROM OnboardingTarefa t WHERE t.onboarding.id = :onboardingId AND t.concluida = true")
    Long countTarefasConcluidasPorOnboarding(Long onboardingId);

    @Query("SELECT COUNT(t) FROM OnboardingTarefa t WHERE t.onboarding.id = :onboardingId")
    Long countTotalTarefasPorOnboarding(Long onboardingId);
}
