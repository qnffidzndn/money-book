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
 * 월급 수정 요청 DTO
 * 월급 내역 수정 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomeUpdateRequest {

    /** 수정할 연월 (형식: yyyy-MM) */
    @NotBlank(message = "연월은 필수입니다.")
    private String yearMonth;

    /** 수정할 월급 금액 */
    @NotNull(message = "금액은 필수입니다.")
    @Positive(message = "금액은 0보다 커야 합니다.")
    private BigDecimal amount;
}
