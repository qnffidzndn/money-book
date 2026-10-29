package com.moneybook.service;

import com.moneybook.domain.SavingsAccount;
import com.moneybook.exception.SavingsAccountNotFoundException;
import com.moneybook.repository.SavingsAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 적금 월별 잔액 서비스
 * 적금 마스터별 월별 잔액 내역의 조회, 저장, 수정 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SavingsAccountService {

    private final SavingsAccountRepository savingsAccountRepository;

    /**
     * 전체 적금 잔액 목록을 조회합니다.
     *
     * @return 전체 적금 잔액 목록
     */
    public List<SavingsAccount> findAll() {
        return savingsAccountRepository.findAll();
    }

    /**
     * ID로 적금 잔액을 조회합니다.
     *
     * @param id 적금 잔액 ID
     * @return 해당 적금 잔액
     * @throws SavingsAccountNotFoundException 적금 잔액이 존재하지 않을 경우
     */
    public SavingsAccount findById(Long id) {
        return savingsAccountRepository.findById(id)
                .orElseThrow(() -> new SavingsAccountNotFoundException(id));
    }

    /**
     * 특정 적금 마스터의 월별 잔액 목록을 조회합니다.
     *
     * @param savingsMasterId 적금 마스터 ID
     * @return 해당 적금 마스터의 월별 잔액 목록
     */
    public List<SavingsAccount> findBySavingsMaster(Long savingsMasterId) {
        return savingsAccountRepository.findBySavingsMasterId(savingsMasterId);
    }

    /**
     * 적금 잔액을 저장합니다.
     *
     * @param savingsAccount 저장할 적금 잔액 엔티티
     * @return 저장된 적금 잔액
     */
    @Transactional
    public SavingsAccount save(SavingsAccount savingsAccount) {
        return savingsAccountRepository.save(savingsAccount);
    }

    /**
     * 적금 잔액의 연월과 금액을 수정합니다.
     *
     * @param id            수정할 적금 잔액 ID
     * @param yearMonth     수정할 연월 (형식: yyyy-MM)
     * @param paymentAmount 수정할 납입 금액
     * @return 수정된 적금 잔액
     * @throws SavingsAccountNotFoundException 적금 잔액이 존재하지 않을 경우
     */
    @Transactional
    public SavingsAccount update(Long id, String yearMonth, BigDecimal paymentAmount) {
        SavingsAccount savingsAccount = savingsAccountRepository.findById(id)
                .orElseThrow(() -> new SavingsAccountNotFoundException(id));
        savingsAccount.update(yearMonth, paymentAmount);
        return savingsAccount;
    }

    @Transactional
    public void delete(Long id) {
        if (!savingsAccountRepository.existsById(id)) {
            throw new SavingsAccountNotFoundException(id);
        }
        savingsAccountRepository.deleteById(id);
    }
}
