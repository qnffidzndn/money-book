package com.moneybook.dto.response;

import com.moneybook.domain.SavingsAccount;
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
public class SavingsAccountResponse {

    /** 적금 잔액 고유 식별자 */
    private Long id;

    /** 적금 마스터 ID */
    private Long savingsMasterId;

    /** 적금 상품명 */
    private String savingsMasterName;

    /** 잔액 기준 연월 (yyyy-MM) */
    private String ym;

    /** 해당 월 납입 금액 */
    private BigDecimal paymentAmount;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    public static SavingsAccountResponse from(SavingsAccount account) {
        return SavingsAccountResponse.builder()
                .id(account.getId())
                .savingsMasterId(account.getSavingsMaster().getId())
                .savingsMasterName(account.getSavingsMaster().getName())
                .ym(account.getYearMonth())
                .paymentAmount(account.getPaymentAmount())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
