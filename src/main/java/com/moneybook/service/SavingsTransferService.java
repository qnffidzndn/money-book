package com.moneybook.service;

import com.moneybook.domain.SavingsTransfer;
import com.moneybook.repository.SavingsTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 적금 만기 자금 이동 서비스
 * 적금 만기 후 자금 이동 내역의 조회 및 저장 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SavingsTransferService {

    private final SavingsTransferRepository savingsTransferRepository;

    /**
     * 특정 적금 상품의 전체 자금 이동 내역을 조회합니다.
     *
     * @param savingsAccountId 적금 상품 ID
     * @return 해당 적금 상품의 자금 이동 내역 목록
     */
    public List<SavingsTransfer> findByAccount(Long savingsAccountId) {
        return savingsTransferRepository.findBySavingsAccountId(savingsAccountId);
    }

    /**
     * 적금 만기 자금 이동 내역을 저장합니다.
     *
     * @param savingsTransfer 저장할 자금 이동 엔티티
     * @return 저장된 자금 이동 내역
     */
    @Transactional
    public SavingsTransfer save(SavingsTransfer savingsTransfer) {
        return savingsTransferRepository.save(savingsTransfer);
    }
}
