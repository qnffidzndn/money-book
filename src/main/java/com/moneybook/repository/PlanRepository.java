package com.moneybook.repository;

import com.moneybook.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 월별 계획 레포지토리
 * 구성원별 월별 지출 및 저축 계획에 대한 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    /**
     * 특정 구성원의 특정 연월 계획 목록을 조회합니다.
     *
     * @param memberId  구성원 ID
     * @param yearMonth 조회할 연월 (형식: yyyy-MM)
     * @return 해당 구성원의 해당 연월 계획 목록
     */
    List<Plan> findByMemberIdAndYearMonth(Long memberId, String yearMonth);

    /**
     * 특정 구성원의 전체 계획 목록을 조회합니다.
     *
     * @param memberId 구성원 ID
     * @return 해당 구성원의 전체 계획 목록
     */
    List<Plan> findByMemberId(Long memberId);
}
