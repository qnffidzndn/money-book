package com.moneybook.dto.response;

import com.moneybook.domain.EmergencyFundLog;
import com.moneybook.domain.TransactionType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 비상금 입출금 내역 응답 DTO
 * 비상금 거래 내역 조회 API의 응답 데이터를 담습니다.
 */
@Getter
@Builder
public class EmergencyFundLogResponse {

    /** 비상금 거래 내역 고유 식별자 */
    private Long id;

    /** 거래 날짜 */
    private LocalDate date;

    /** 거래 금액 */
    private BigDecimal amount;

    /** 거래 유형 (IN: 입금, OUT: 출금) */
    private TransactionType type;

    /** 메모 */
    private String memo;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    /**
     * EmergencyFundLog 엔티티로부터 응답 DTO를 생성합니다.
     *
     * @param log 변환할 EmergencyFundLog 엔티티
     * @return EmergencyFundLogResponse DTO
     */
    public static EmergencyFundLogResponse from(EmergencyFundLog log) {
        return EmergencyFundLogResponse.builder()
                .id(log.getId())
                .date(log.getDate())
                .amount(log.getAmount())
                .type(log.getType())
                .memo(log.getMemo())
                .createdAt(log.getCreatedAt())
                .build();
    }
}
