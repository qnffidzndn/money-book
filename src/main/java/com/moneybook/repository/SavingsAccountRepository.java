package com.moneybook.repository;

import com.moneybook.domain.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 적금 월별 잔액 레포지토리
 * 적금 마스터별 월별 잔액 내역에 대한 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

    /**
     * 특정 적금 마스터의 전체 월별 잔액 목록을 조회합니다.
     *
     * @param savingsMasterId 적금 마스터 ID
     * @return 해당 적금 마스터의 월별 잔액 목록
     */
    List<SavingsAccount> findBySavingsMasterId(Long savingsMasterId);
}
