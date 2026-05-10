package com.moneybook.service;

import com.moneybook.domain.SavingsAccount;
import com.moneybook.exception.SavingsAccountNotFoundException;
import com.moneybook.repository.SavingsAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 적금 상품 서비스
 * 구성원별 적금 상품 조회, 저장, 수정 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SavingsAccountService {

    private final SavingsAccountRepository savingsAccountRepository;

    /**
     * ID로 적금 상품을 조회합니다.
     *
     * @param id 적금 상품 ID
     * @return 해당 적금 상품
     * @throws SavingsAccountNotFoundException 적금 상품이 존재하지 않을 경우
     */
    public SavingsAccount findById(Long id) {
        return savingsAccountRepository.findById(id)
                .orElseThrow(() -> new SavingsAccountNotFoundException(id));
    }

    /**
     * 전체 적금 상품 목록을 조회합니다.
     *
     * @return 전체 적금 상품 목록
     */
    public List<SavingsAccount> findAll() {
        return savingsAccountRepository.findAll();
    }

    /**
     * 특정 구성원의 전체 적금 상품 목록을 조회합니다.
     *
     * @param memberId 구성원 ID
     * @return 해당 구성원의 적금 상품 목록
     */
    public List<SavingsAccount> findByMember(Long memberId) {
        return savingsAccountRepository.findByMemberId(memberId);
    }

    /**
     * 기준 날짜 이전에 만기가 도래한 적금 상품 목록을 조회합니다.
     *
     * @param referenceDate 기준 날짜 (보통 오늘 날짜를 전달)
     * @return 만기 도래 적금 상품 목록
     */
    public List<SavingsAccount> findExpiringSoon(LocalDate referenceDate) {
        return savingsAccountRepository.findByMaturityDateBefore(referenceDate);
    }

    /**
     * 적금 상품을 저장합니다.
     *
     * @param savingsAccount 저장할 적금 상품 엔티티
     * @return 저장된 적금 상품
     */
    @Transactional
    public SavingsAccount save(SavingsAccount savingsAccount) {
        return savingsAccountRepository.save(savingsAccount);
    }

    /**
     * 적금 상품 정보를 수정합니다.
     *
     * @param id            수정할 적금 상품 ID
     * @param name          수정할 상품명
     * @param bank          수정할 은행명
     * @param monthlyAmount 수정할 월 납입액
     * @param maturityDate  수정할 만기일
     * @return 수정된 적금 상품
     * @throws SavingsAccountNotFoundException 적금 상품이 존재하지 않을 경우
     */
    @Transactional
    public SavingsAccount update(Long id, String name, String bank, BigDecimal monthlyAmount, LocalDate maturityDate) {
        SavingsAccount savingsAccount = savingsAccountRepository.findById(id)
                .orElseThrow(() -> new SavingsAccountNotFoundException(id));
        savingsAccount.update(name, bank, monthlyAmount, maturityDate);
        return savingsAccount;
    }
}
