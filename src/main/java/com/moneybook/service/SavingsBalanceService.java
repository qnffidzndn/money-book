package com.moneybook.service;

import com.moneybook.domain.SavingsBalance;
import com.moneybook.repository.SavingsBalanceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * 적금 월별 잔액 서비스
 * 적금 상품의 월별 잔액 추이 조회, 저장, 수정 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SavingsBalanceService {

    private final SavingsBalanceRepository savingsBalanceRepository;

    /**
     * 특정 적금 상품의 특정 연월 잔액을 조회합니다.
     *
     * @param savingsAccountId 적금 상품 ID
     * @param yearMonth        조회할 연월 (형식: yyyy-MM)
     * @return 해당 적금 상품의 해당 연월 잔액 (없으면 empty)
     */
    public Optional<SavingsBalance> findByAccountAndYm(Long savingsAccountId, String yearMonth) {
        return savingsBalanceRepository.findBySavingsAccountIdAndYearMonth(savingsAccountId, yearMonth);
    }

    /**
     * 적금 월별 잔액을 저장합니다.
     *
     * @param savingsBalance 저장할 적금 잔액 엔티티
     * @return 저장된 적금 잔액
     */
    @Transactional
    public SavingsBalance save(SavingsBalance savingsBalance) {
        return savingsBalanceRepository.save(savingsBalance);
    }

    /**
     * 적금 월별 잔액을 수정합니다.
     *
     * @param id      수정할 적금 잔액 ID
     * @param balance 수정할 잔액
     * @return 수정된 적금 잔액
     * @throws EntityNotFoundException 적금 잔액 내역이 존재하지 않을 경우
     */
    @Transactional
    public SavingsBalance update(Long id, BigDecimal balance) {
        SavingsBalance savingsBalance = savingsBalanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("적금 잔액 내역을 찾을 수 없습니다. id=" + id));
        savingsBalance.update(balance);
        return savingsBalance;
    }
}
