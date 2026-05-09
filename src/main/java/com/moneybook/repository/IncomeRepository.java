package com.moneybook.repository;

import com.moneybook.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 월급 레포지토리
 * 구성원별 월급 내역에 대한 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    /**
     * 특정 구성원의 특정 연월 월급 내역을 조회합니다.
     *
     * @param memberId  구성원 ID
     * @param yearMonth 조회할 연월 (형식: yyyy-MM)
     * @return 해당 구성원의 해당 연월 월급 목록
     */
    List<Income> findByMemberIdAndYearMonth(Long memberId, String yearMonth);

    /**
     * 특정 구성원의 전체 월급 내역을 조회합니다.
     *
     * @param memberId 구성원 ID
     * @return 해당 구성원의 전체 월급 목록
     */
    List<Income> findByMemberId(Long memberId);
}
