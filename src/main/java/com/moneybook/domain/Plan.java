package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 월별 계획 엔티티
 * 구성원이 특정 월에 카테고리별로 설정한 지출 또는 저축 계획 금액을 관리합니다.
 */
@Entity
@Table(name = "plan")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Plan extends BaseEntity {

    /**
     * 계획 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 계획을 세운 구성원
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    /**
     * 계획이 속한 카테고리 (지출 또는 저축 항목)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * 계획 대상 연월 (형식: yyyy-MM, 예: 2025-01)
     */
    @Column(name = "ym", nullable = false, length = 7)
    private String yearMonth;

    /**
     * 해당 월 카테고리에 배정한 계획 금액 (원 단위)
     */
    @Column(name = "planned_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal plannedAmount;

    /**
     * 이 계획에 대한 실제 사용 내역 목록
     */
    @Builder.Default
    @OneToMany(mappedBy = "plan", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActualSpending> actualSpendings = new ArrayList<>();

    /**
     * 계획의 연월과 계획 금액을 수정합니다.
     *
     * @param yearMonth     수정할 연월 (형식: yyyy-MM)
     * @param plannedAmount 수정할 계획 금액
     */
    public void update(String yearMonth, BigDecimal plannedAmount) {
        this.yearMonth = yearMonth;
        this.plannedAmount = plannedAmount;
    }
}
