package com.moneybook.dto.request;

import com.moneybook.domain.TransactionType;
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
 * 비상금 입출금 내역 수정 요청 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergencyFundLogUpdateRequest {

    @NotNull(message = "거래 날짜는 필수입니다.")
    private LocalDate date;

    @NotNull(message = "금액은 필수입니다.")
    @Positive(message = "금액은 0보다 커야 합니다.")
    private BigDecimal amount;

    @NotNull(message = "거래 유형은 필수입니다.")
    private TransactionType type;

    @Size(max = 255, message = "메모는 255자 이하여야 합니다.")
    private String memo;
}
