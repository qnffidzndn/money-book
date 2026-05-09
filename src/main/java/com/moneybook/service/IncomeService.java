package com.moneybook.service;

import com.moneybook.domain.Income;
import com.moneybook.exception.IncomeNotFoundException;
import com.moneybook.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 월급 서비스
 * 구성원별 월급 내역 조회, 저장, 수정, 삭제 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IncomeService {

    private final IncomeRepository incomeRepository;

    /**
     * 특정 구성원의 특정 연월 월급 내역을 조회합니다.
     *
     * @param memberId  구성원 ID
     * @param yearMonth 조회할 연월 (형식: yyyy-MM)
     * @return 해당 구성원의 해당 연월 월급 목록
     */
    public List<Income> findByMemberAndYm(Long memberId, String yearMonth) {
        return incomeRepository.findByMemberIdAndYearMonth(memberId, yearMonth);
    }

    /**
     * 특정 구성원의 전체 월급 내역을 조회합니다.
     *
     * @param memberId 구성원 ID
     * @return 해당 구성원의 전체 월급 목록
     */
    public List<Income> findByMember(Long memberId) {
        return incomeRepository.findByMemberId(memberId);
    }

    /**
     * 월급 내역을 저장합니다.
     *
     * @param income 저장할 월급 엔티티
     * @return 저장된 월급 내역
     */
    @Transactional
    public Income save(Income income) {
        return incomeRepository.save(income);
    }

    /**
     * 월급 내역의 연월과 금액을 수정합니다.
     *
     * @param id        수정할 월급 내역 ID
     * @param yearMonth 수정할 연월 (형식: yyyy-MM)
     * @param amount    수정할 금액
     * @return 수정된 월급 내역
     * @throws IncomeNotFoundException 월급 내역이 존재하지 않을 경우
     */
    @Transactional
    public Income update(Long id, String yearMonth, BigDecimal amount) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new IncomeNotFoundException(id));
        income.update(yearMonth, amount);
        return income;
    }

    /**
     * 월급 내역을 삭제합니다.
     *
     * @param id 삭제할 월급 내역 ID
     * @throws IncomeNotFoundException 월급 내역이 존재하지 않을 경우
     */
    @Transactional
    public void delete(Long id) {
        if (!incomeRepository.existsById(id)) {
            throw new IncomeNotFoundException(id);
        }
        incomeRepository.deleteById(id);
    }
}
