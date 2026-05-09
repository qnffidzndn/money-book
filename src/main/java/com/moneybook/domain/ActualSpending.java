package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 실제 사용 내역 엔티티
 * 월별 계획(Plan)에 대해 실제로 지출하거나 저축한 금액과 세부 메모를 기록합니다.
 */
@Entity
@Table(name = "actual_spending")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ActualSpending extends BaseEntity {

    /**
     * 실제 사용 내역 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 이 사용 내역이 속한 월별 계획
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    /**
     * 실제 지출 또는 저축 금액 (원 단위)
     */
    @Column(name = "actual_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal actualAmount;

    /**
     * 사용 내역에 대한 메모 (예: 마트 장보기, 카페 등)
     */
    @Column(length = 255)
    private String memo;

    /**
     * 실제 사용 금액과 메모를 수정합니다.
     *
     * @param actualAmount 수정할 실제 사용 금액
     * @param memo         수정할 메모
     */
    public void update(BigDecimal actualAmount, String memo) {
        this.actualAmount = actualAmount;
        this.memo = memo;
    }
}
