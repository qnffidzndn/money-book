package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 자산 배분 엔티티
 * 구성원의 자산 항목과 금액을 기록합니다.
 */
@Entity
@Table(name = "asset_allocation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AssetAllocation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asset_date", nullable = false, columnDefinition = "DATE COMMENT '자산 기준일자'")
    private LocalDate assetDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_type_id", nullable = false, columnDefinition = "BIGINT COMMENT '자산 유형 마스터 FK'")
    private AssetTypeMaster assetType;

    @Column(nullable = false, precision = 15, scale = 2, columnDefinition = "DECIMAL(15, 2) COMMENT '자산 금액 (원 단위)'")
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, columnDefinition = "BIGINT COMMENT '구성원 FK'")
    private Member member;

    @Column(length = 255, columnDefinition = "VARCHAR(255) COMMENT '자산 설명'")
    private String description;

    public void update(LocalDate assetDate, AssetTypeMaster assetType, BigDecimal amount, String description) {
        this.assetDate = assetDate;
        this.assetType = assetType;
        this.amount = amount;
        this.description = description;
    }
}
