package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 적금 만기 자금 이동 엔티티
 * 적금 만기 후 수령한 자금이 어느 계좌로 이동했는지 기록합니다.
 */
@Entity
@Table(name = "savings_transfer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SavingsTransfer extends BaseEntity {

    /**
     * 자금 이동 내역 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 만기가 된 적금 상품
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "savings_account_id", nullable = false)
    private SavingsAccount savingsAccount;

    /**
     * 적금 만기 수령 또는 자금 이동 실행 날짜
     */
    @Column(name = "transfer_date", nullable = false)
    private LocalDate transferDate;

    /**
     * 이동된 자금 금액 (원 단위)
     */
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    /**
     * 자금이 이동된 목적지 계좌 또는 용도 (예: 주택청약, 생활비 통장)
     */
    @Column(name = "to_account", length = 100)
    private String toAccount;
}
