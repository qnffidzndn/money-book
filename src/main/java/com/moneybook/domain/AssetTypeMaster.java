package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 자산 유형 마스터 엔티티
 * 자산의 세부 유형명을 관리합니다.
 */
@Entity
@Table(name = "asset_type_master")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AssetTypeMaster extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, columnDefinition = "VARCHAR(100) COMMENT '자산 유형명'")
    private String name;

    public void update(String name) {
        this.name = name;
    }
}
