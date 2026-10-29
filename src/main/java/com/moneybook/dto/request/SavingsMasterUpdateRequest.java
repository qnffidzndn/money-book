package com.moneybook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 적금 마스터 수정 요청 DTO
 * 적금 마스터 수정 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsMasterUpdateRequest {

    /** 수정할 상품명 */
    @NotBlank(message = "상품명은 필수입니다.")
    private String name;

    /** 수정할 은행 마스터 ID */
    @NotNull(message = "은행 ID는 필수입니다.")
    private Long bankId;

    /** 수정할 월 납입액 */
    @NotNull(message = "월 납입액은 필수입니다.")
    @Positive(message = "월 납입액은 0보다 커야 합니다.")
    private BigDecimal monthlyAmount;

    /** 수정할 시작일 */
    private LocalDate startDate;

    /** 수정할 만기일 */
    private LocalDate maturityDate;

    /** 수정할 금리 (%) */
    private BigDecimal interestRate;

    /** 수정할 예상 이자 */
    private BigDecimal expectedInterest;

    /** 수정할 실제 받은 이자 */
    private BigDecimal actualInterest;
}
