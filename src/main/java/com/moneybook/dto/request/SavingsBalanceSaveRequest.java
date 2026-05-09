package com.moneybook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 적금 월별 잔액 저장 요청 DTO
 * 적금 잔액 생성 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsBalanceSaveRequest {

    /** 적금 상품 ID */
    @NotNull(message = "적금 상품 ID는 필수입니다.")
    private Long savingsAccountId;

    /** 연월 (형식: yyyy-MM) */
    @NotBlank(message = "연월은 필수입니다.")
    private String yearMonth;

    /** 잔액 */
    @NotNull(message = "잔액은 필수입니다.")
    @Positive(message = "잔액은 0보다 커야 합니다.")
    private BigDecimal balance;
}
