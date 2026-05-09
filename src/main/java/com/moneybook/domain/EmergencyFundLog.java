package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 비상금 입출금 내역 엔티티
 * 비상금 계좌의 입금(IN) 및 출금(OUT) 거래 이력을 기록합니다.
 * 비상금 잔액은 IN/OUT 내역의 누적 합산으로 계산합니다.
 */
@Entity
@Table(name = "emergency_fund_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EmergencyFundLog extends BaseEntity {

    /**
     * 비상금 거래 내역 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 거래 발생 날짜
     */
    @Column(nullable = false)
    private LocalDate date;

    /**
     * 거래 금액 (원 단위, 양수값으로 저장하며 type으로 입출금 구분)
     */
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    /**
     * 거래 유형 (IN: 입금, OUT: 출금)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private TransactionType type;

    /**
     * 거래 사유 또는 메모 (예: 긴급 의료비, 월급 이체 등)
     */
    @Column(length = 255)
    private String memo;
}
