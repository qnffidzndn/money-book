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
 * 적금 마스터 저장 요청 DTO
 * 적금 마스터 생성 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsMasterSaveRequest {

    /** 구성원 ID */
    @NotNull(message = "구성원 ID는 필수입니다.")
    private Long memberId;

    /** 적금 상품명 */
    @NotBlank(message = "상품명은 필수입니다.")
    private String name;

    /** 은행 마스터 ID */
    @NotNull(message = "은행 ID는 필수입니다.")
    private Long bankId;

    /** 월 납입액 */
    @NotNull(message = "월 납입액은 필수입니다.")
    @Positive(message = "월 납입액은 0보다 커야 합니다.")
    private BigDecimal monthlyAmount;

    /** 시작일 */
    private LocalDate startDate;

    /** 만기일 */
    private LocalDate maturityDate;

    /** 금리 (%) */
    private BigDecimal interestRate;

    /** 예상 이자 */
    private BigDecimal expectedInterest;

    /** 실제 받은 이자 */
    private BigDecimal actualInterest;
}
