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
 * 비상금 입출금 내역 저장 요청 DTO
 * 비상금 거래 내역 생성 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergencyFundLogSaveRequest {

    /** 거래 날짜 */
    @NotNull(message = "거래 날짜는 필수입니다.")
    private LocalDate date;

    /** 거래 금액 */
    @NotNull(message = "금액은 필수입니다.")
    @Positive(message = "금액은 0보다 커야 합니다.")
    private BigDecimal amount;

    /** 거래 유형 (IN: 입금, OUT: 출금) */
    @NotNull(message = "거래 유형은 필수입니다.")
    private TransactionType type;

    /** 메모 */
    @Size(max = 255, message = "메모는 255자 이하여야 합니다.")
    private String memo;
}
