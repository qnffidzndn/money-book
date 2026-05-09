package com.moneybook.dto.request;

import jakarta.validation.constraints.NotBlank;
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
 * 적금 상품 수정 요청 DTO
 * 적금 상품 수정 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsAccountUpdateRequest {

    /** 수정할 상품명 */
    @NotBlank(message = "상품명은 필수입니다.")
    @Size(max = 100, message = "상품명은 100자 이하여야 합니다.")
    private String name;

    /** 수정할 은행명 */
    @NotBlank(message = "은행명은 필수입니다.")
    @Size(max = 100, message = "은행명은 100자 이하여야 합니다.")
    private String bank;

    /** 수정할 월 납입액 */
    @NotNull(message = "월 납입액은 필수입니다.")
    @Positive(message = "월 납입액은 0보다 커야 합니다.")
    private BigDecimal monthlyAmount;

    /** 수정할 만기일 */
    @NotNull(message = "만기일은 필수입니다.")
    private LocalDate maturityDate;
}
