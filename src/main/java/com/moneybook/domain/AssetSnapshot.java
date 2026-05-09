package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 월별 총자산 스냅샷 엔티티
 * 매월 말 기준으로 두 구성원의 합산 총자산을 기록합니다.
 * 자산 변화 추이를 월 단위로 추적하는 데 활용됩니다.
 */
@Entity
@Table(name = "asset_snapshot")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AssetSnapshot extends BaseEntity {

    /**
     * 자산 스냅샷 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 자산 집계 기준 연월 (형식: yyyy-MM, 예: 2025-01)
     */
    @Column(name = "ym", nullable = false, length = 7)
    private String yearMonth;

    /**
     * 해당 월 말 기준 두 구성원의 합산 총자산 (원 단위)
     */
    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    /**
     * 해당 월 자산에 대한 메모 (예: 연말정산 환급금 포함 등)
     */
    @Column(length = 255)
    private String memo;

    /**
     * 총자산 금액과 메모를 수정합니다.
     *
     * @param totalAmount 수정할 총자산 금액
     * @param memo        수정할 메모
     */
    public void update(BigDecimal totalAmount, String memo) {
        this.totalAmount = totalAmount;
        this.memo = memo;
    }
}
