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
 * 적금 잔액 등록 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsAccountSaveRequest {

    /** 적금 마스터 ID */
    @NotNull(message = "적금 마스터 ID는 필수입니다.")
    private Long savingsMasterId;

    /** 잔액 기준 연월 (형식: yyyy-MM) */
    @NotBlank(message = "연월은 필수입니다.")
    private String ym;

    /** 해당 월 납입 금액 */
    @NotNull(message = "납입 금액은 필수입니다.")
    @Positive(message = "납입 금액은 0보다 커야 합니다.")
    private BigDecimal paymentAmount;
}
