package com.moneybook.repository;

import com.moneybook.domain.SavingsTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 적금 만기 자금 이동 레포지토리
 * 적금 만기 후 자금 이동 내역에 대한 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface SavingsTransferRepository extends JpaRepository<SavingsTransfer, Long> {

    /**
     * 특정 적금 상품의 전체 자금 이동 내역을 조회합니다.
     *
     * @param savingsAccountId 적금 상품 ID
     * @return 해당 적금 상품의 자금 이동 내역 목록
     */
    List<SavingsTransfer> findBySavingsAccountId(Long savingsAccountId);
}
