package com.moneybook.repository;

import com.moneybook.domain.ActualSpending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 실제 사용 내역 레포지토리
 * 월별 계획에 대한 실제 지출 및 저축 내역의 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface ActualSpendingRepository extends JpaRepository<ActualSpending, Long> {

    /**
     * 특정 계획에 속한 실제 사용 내역 목록을 조회합니다.
     *
     * @param planId 계획 ID
     * @return 해당 계획의 실제 사용 내역 목록
     */
    List<ActualSpending> findByPlanId(Long planId);
}
