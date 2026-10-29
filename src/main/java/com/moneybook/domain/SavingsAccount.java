package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 적금 월별 잔액 엔티티
 * 적금 마스터(SavingsMaster)에 연결된 월별 잔액 내역을 기록합니다.
 * 매월 납입 후 누적 잔액을 연월(ym) 기준으로 스냅샷 형태로 저장합니다.
 */
@Entity
@Table(name = "savings_account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SavingsAccount extends BaseEntity {

    /**
     * 적금 잔액 내역 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 연결된 적금 마스터 상품
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "savings_master_id", nullable = false)
    private SavingsMaster savingsMaster;

    /**
     * 잔액 기준 연월 (형식: yyyy-MM, 예: 2026-07)
     */
    @Column(name = "ym", nullable = false, length = 7, columnDefinition = "VARCHAR(7) COMMENT '잔액 기준 연월 (yyyy-MM)'")
    private String yearMonth;

    /**
     * 해당 월 납입 금액 (원 단위)
     */
    @Column(name = "payment_amount", nullable = false, precision = 15, scale = 2, columnDefinition = "DECIMAL(15, 2) COMMENT '해당 월 납입 금액 (원 단위)'")
    private BigDecimal paymentAmount;

    /**
     * 연월과 납입 금액을 수정합니다.
     *
     * @param yearMonth     수정할 연월 (형식: yyyy-MM)
     * @param paymentAmount 수정할 납입 금액
     */
    public void update(String yearMonth, BigDecimal paymentAmount) {
        this.yearMonth = yearMonth;
        this.paymentAmount = paymentAmount;
    }
}
