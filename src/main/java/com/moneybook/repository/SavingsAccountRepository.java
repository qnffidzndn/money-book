package com.moneybook.repository;

import com.moneybook.domain.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 적금 상품 레포지토리
 * 구성원별 적금 상품 정보에 대한 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

    /**
     * 특정 구성원의 전체 적금 상품 목록을 조회합니다.
     *
     * @param memberId 구성원 ID
     * @return 해당 구성원의 적금 상품 목록
     */
    List<SavingsAccount> findByMemberId(Long memberId);

    /**
     * 특정 날짜 이전에 만기가 도래한 적금 상품 목록을 조회합니다.
     *
     * @param date 기준 날짜
     * @return 해당 날짜 이전 만기 적금 상품 목록
     */
    List<SavingsAccount> findByMaturityDateBefore(LocalDate date);
}
