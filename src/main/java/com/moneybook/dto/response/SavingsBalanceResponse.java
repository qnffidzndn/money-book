package com.moneybook.dto.response;

import com.moneybook.domain.SavingsBalance;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 적금 월별 잔액 응답 DTO
 * 적금 잔액 조회 API의 응답 데이터를 담습니다.
 */
@Getter
@Builder
public class SavingsBalanceResponse {

    /** 적금 잔액 내역 고유 식별자 */
    private Long id;

    /** 적금 상품 ID */
    private Long savingsAccountId;

    /** 연월 (형식: yyyy-MM) */
    private String yearMonth;

    /** 잔액 */
    private BigDecimal balance;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    /**
     * SavingsBalance 엔티티로부터 응답 DTO를 생성합니다.
     *
     * @param balance 변환할 SavingsBalance 엔티티
     * @return SavingsBalanceResponse DTO
     */
    public static SavingsBalanceResponse from(SavingsBalance balance) {
        return SavingsBalanceResponse.builder()
                .id(balance.getId())
                .savingsAccountId(balance.getSavingsAccount().getId())
                .yearMonth(balance.getYearMonth())
                .balance(balance.getBalance())
                .createdAt(balance.getCreatedAt())
                .build();
    }
}
