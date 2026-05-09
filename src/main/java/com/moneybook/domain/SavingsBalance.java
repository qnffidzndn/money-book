package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 적금 월별 잔액 엔티티
 * 적금 상품의 월별 누적 잔액 추이를 기록합니다.
 * 매월 말 잔액을 스냅샷 형태로 저장하여 적금 성장 현황을 추적합니다.
 */
@Entity
@Table(name = "savings_balance")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SavingsBalance extends BaseEntity {

    /**
     * 적금 잔액 내역 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 잔액이 속한 적금 상품
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "savings_account_id", nullable = false)
    private SavingsAccount savingsAccount;

    /**
     * 잔액 기준 연월 (형식: yyyy-MM, 예: 2025-01)
     */
    @Column(name = "ym", nullable = false, length = 7)
    private String yearMonth;

    /**
     * 해당 월 말 기준 적금 누적 잔액 (원 단위)
     */
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    /**
     * 적금 잔액을 수정합니다.
     *
     * @param balance 수정할 잔액
     */
    public void update(BigDecimal balance) {
        this.balance = balance;
    }
}
