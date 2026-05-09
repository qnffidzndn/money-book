package com.moneybook.repository;

import com.moneybook.domain.SavingsBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 적금 월별 잔액 레포지토리
 * 적금 상품의 월별 누적 잔액 추이에 대한 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface SavingsBalanceRepository extends JpaRepository<SavingsBalance, Long> {

    /**
     * 특정 적금 상품의 특정 연월 잔액을 조회합니다.
     *
     * @param savingsAccountId 적금 상품 ID
     * @param yearMonth        조회할 연월 (형식: yyyy-MM)
     * @return 해당 적금 상품의 해당 연월 잔액 (없으면 empty)
     */
    Optional<SavingsBalance> findBySavingsAccountIdAndYearMonth(Long savingsAccountId, String yearMonth);
}
