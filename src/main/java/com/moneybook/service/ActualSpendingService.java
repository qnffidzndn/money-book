package com.moneybook.service;

import com.moneybook.domain.ActualSpending;
import com.moneybook.domain.Plan;
import com.moneybook.repository.ActualSpendingRepository;
import com.moneybook.repository.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 실제 사용 내역 서비스
 * 월별 계획 대비 실제 지출 및 저축 내역의 조회, 저장, 수정 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActualSpendingService {

    private final ActualSpendingRepository actualSpendingRepository;
    private final PlanRepository planRepository;

    /**
     * 특정 계획에 속한 실제 사용 내역 목록을 조회합니다.
     *
     * @param planId 계획 ID
     * @return 해당 계획의 실제 사용 내역 목록
     */
    public List<ActualSpending> findByPlan(Long planId) {
        return actualSpendingRepository.findByPlanId(planId);
    }

    /**
     * 실제 사용 내역을 저장합니다.
     *
     * @param spending 저장할 실제 사용 내역 엔티티
     * @return 저장된 실제 사용 내역
     */
    @Transactional
    public ActualSpending save(ActualSpending spending) {
        return actualSpendingRepository.save(spending);
    }

    /**
     * 실제 사용 금액과 메모를 수정합니다.
     *
     * @param id           수정할 실제 사용 내역 ID
     * @param actualAmount 수정할 실제 사용 금액
     * @param memo         수정할 메모
     * @return 수정된 실제 사용 내역
     * @throws EntityNotFoundException 실제 사용 내역이 존재하지 않을 경우
     */
    @Transactional
    public ActualSpending update(Long id, BigDecimal actualAmount, String memo) {
        ActualSpending spending = actualSpendingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("실제 사용 내역을 찾을 수 없습니다. id=" + id));
        spending.update(actualAmount, memo);
        return spending;
    }

    /**
     * 특정 구성원의 특정 연월 실제 지출 합계를 계산합니다.
     * 해당 연월의 모든 계획에 속한 실제 사용 금액을 합산합니다.
     *
     * @param memberId  구성원 ID
     * @param yearMonth 조회할 연월 (형식: yyyy-MM)
     * @return 해당 월 실제 지출 합계
     */
    public BigDecimal getTotalActualByMemberAndYm(Long memberId, String yearMonth) {
        List<Plan> plans = planRepository.findByMemberIdAndYearMonth(memberId, yearMonth);
        return plans.stream()
                .flatMap(plan -> actualSpendingRepository.findByPlanId(plan.getId()).stream())
                .map(ActualSpending::getActualAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
