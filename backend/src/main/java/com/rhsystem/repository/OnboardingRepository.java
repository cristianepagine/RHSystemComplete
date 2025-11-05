package com.rhsystem.repository;

import com.rhsystem.model.Onboarding;
import com.rhsystem.model.StatusOnboarding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OnboardingRepository extends JpaRepository<Onboarding, Long> {
    Optional<Onboarding> findByFuncionarioId(Long funcionarioId);
    List<Onboarding> findByStatus(StatusOnboarding status);
    List<Onboarding> findByResponsavelId(Long responsavelId);

    @Query("SELECT COUNT(o) FROM Onboarding o WHERE o.status = 'EM_ANDAMENTO'")
    Long countEmAndamento();
}
