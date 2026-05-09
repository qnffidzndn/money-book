package com.moneybook.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 적금 만기 자금 이동 저장 요청 DTO
 * 적금 자금 이동 내역 생성 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsTransferSaveRequest {

    /** 적금 상품 ID */
    @NotNull(message = "적금 상품 ID는 필수입니다.")
    private Long savingsAccountId;

    /** 자금 이동 날짜 */
    @NotNull(message = "이동 날짜는 필수입니다.")
    private LocalDate transferDate;

    /** 이동 금액 */
    @NotNull(message = "이동 금액은 필수입니다.")
    @Positive(message = "이동 금액은 0보다 커야 합니다.")
    private BigDecimal amount;

    /** 이동 목적지 계좌 또는 용도 */
    @Size(max = 100, message = "목적지는 100자 이하여야 합니다.")
    private String toAccount;
}
