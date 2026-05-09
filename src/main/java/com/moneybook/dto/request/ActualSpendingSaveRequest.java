package com.moneybook.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 실제 사용 내역 저장 요청 DTO
 * 실제 사용 내역 생성 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualSpendingSaveRequest {

    /** 계획 ID */
    @NotNull(message = "계획 ID는 필수입니다.")
    private Long planId;

    /** 실제 사용 금액 */
    @NotNull(message = "금액은 필수입니다.")
    @Positive(message = "금액은 0보다 커야 합니다.")
    private BigDecimal actualAmount;

    /** 메모 */
    @Size(max = 255, message = "메모는 255자 이하여야 합니다.")
    private String memo;
}
