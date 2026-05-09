package com.moneybook.dto.response;

import com.moneybook.domain.SavingsTransfer;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 적금 만기 자금 이동 응답 DTO
 * 적금 자금 이동 내역 조회 API의 응답 데이터를 담습니다.
 */
@Getter
@Builder
public class SavingsTransferResponse {

    /** 자금 이동 내역 고유 식별자 */
    private Long id;

    /** 적금 상품 ID */
    private Long savingsAccountId;

    /** 자금 이동 날짜 */
    private LocalDate transferDate;

    /** 이동 금액 */
    private BigDecimal amount;

    /** 이동 목적지 계좌 또는 용도 */
    private String toAccount;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    /**
     * SavingsTransfer 엔티티로부터 응답 DTO를 생성합니다.
     *
     * @param transfer 변환할 SavingsTransfer 엔티티
     * @return SavingsTransferResponse DTO
     */
    public static SavingsTransferResponse from(SavingsTransfer transfer) {
        return SavingsTransferResponse.builder()
                .id(transfer.getId())
                .savingsAccountId(transfer.getSavingsAccount().getId())
                .transferDate(transfer.getTransferDate())
                .amount(transfer.getAmount())
                .toAccount(transfer.getToAccount())
                .createdAt(transfer.getCreatedAt())
                .build();
    }
}
