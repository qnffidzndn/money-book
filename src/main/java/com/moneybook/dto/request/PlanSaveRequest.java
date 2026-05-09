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
 * 월별 계획 저장 요청 DTO
 * 월별 계획 생성 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanSaveRequest {

    /** 구성원 ID */
    @NotNull(message = "구성원 ID는 필수입니다.")
    private Long memberId;

    /** 카테고리 ID */
    @NotNull(message = "카테고리 ID는 필수입니다.")
    private Long categoryId;

    /** 연월 (형식: yyyy-MM) */
    @NotBlank(message = "연월은 필수입니다.")
    private String yearMonth;

    /** 계획 금액 */
    @NotNull(message = "계획 금액은 필수입니다.")
    @Positive(message = "계획 금액은 0보다 커야 합니다.")
    private BigDecimal plannedAmount;
}
