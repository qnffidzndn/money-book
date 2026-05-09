package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 월급 엔티티
 * 구성원별 월별 월급 수입 금액을 기록합니다.
 */
@Entity
@Table(name = "income")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Income extends BaseEntity {

    /**
     * 월급 내역 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 월급을 받은 구성원
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    /**
     * 월급 귀속 연월 (형식: yyyy-MM, 예: 2025-01)
     */
    @Column(name = "ym", nullable = false, length = 7)
    private String yearMonth;

    /**
     * 해당 월 월급 금액 (원 단위)
     */
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    /**
     * 월급 내역의 연월과 금액을 수정합니다.
     *
     * @param yearMonth 수정할 연월 (형식: yyyy-MM)
     * @param amount    수정할 금액
     */
    public void update(String yearMonth, BigDecimal amount) {
        this.yearMonth = yearMonth;
        this.amount = amount;
    }
}
