package com.moneybook.service;

import com.moneybook.domain.Plan;
import com.moneybook.exception.PlanNotFoundException;
import com.moneybook.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 월별 계획 서비스
 * 구성원별 월별 지출 및 저축 계획의 조회, 저장, 수정, 삭제 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;

    /**
     * ID로 계획을 조회합니다.
     *
     * @param id 계획 ID
     * @return 해당 계획
     * @throws PlanNotFoundException 계획이 존재하지 않을 경우
     */
    public Plan findById(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new PlanNotFoundException(id));
    }

    /**
     * 특정 구성원의 특정 연월 계획 목록을 조회합니다.
     *
     * @param memberId  구성원 ID
     * @param yearMonth 조회할 연월 (형식: yyyy-MM)
     * @return 해당 구성원의 해당 연월 계획 목록
     */
    public List<Plan> findByMemberAndYm(Long memberId, String yearMonth) {
        return planRepository.findByMemberIdAndYearMonth(memberId, yearMonth);
    }

    /**
     * 특정 구성원의 전체 계획 목록을 조회합니다.
     *
     * @param memberId 구성원 ID
     * @return 해당 구성원의 전체 계획 목록
     */
    public List<Plan> findByMember(Long memberId) {
        return planRepository.findByMemberId(memberId);
    }

    /**
     * 계획을 저장합니다.
     *
     * @param plan 저장할 계획 엔티티
     * @return 저장된 계획
     */
    @Transactional
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    /**
     * 계획의 연월과 계획 금액을 수정합니다.
     *
     * @param id            수정할 계획 ID
     * @param yearMonth     수정할 연월 (형식: yyyy-MM)
     * @param plannedAmount 수정할 계획 금액
     * @return 수정된 계획
     * @throws PlanNotFoundException 계획이 존재하지 않을 경우
     */
    @Transactional
    public Plan update(Long id, String yearMonth, BigDecimal plannedAmount) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new PlanNotFoundException(id));
        plan.update(yearMonth, plannedAmount);
        return plan;
    }

    /**
     * 계획을 삭제합니다.
     *
     * @param id 삭제할 계획 ID
     * @throws PlanNotFoundException 계획이 존재하지 않을 경우
     */
    @Transactional
    public void delete(Long id) {
        if (!planRepository.existsById(id)) {
            throw new PlanNotFoundException(id);
        }
        planRepository.deleteById(id);
    }

    /**
     * 특정 구성원의 특정 연월 전체 계획 금액 합계를 계산합니다.
     *
     * @param memberId  구성원 ID
     * @param yearMonth 조회할 연월 (형식: yyyy-MM)
     * @return 해당 월 계획 금액 합계
     */
    public BigDecimal getTotalPlannedByMemberAndYm(Long memberId, String yearMonth) {
        return planRepository.findByMemberIdAndYearMonth(memberId, yearMonth).stream()
                .map(Plan::getPlannedAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
