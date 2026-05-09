package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리 엔티티
 * 지출 및 저축 항목을 분류하는 카테고리 정보를 관리합니다.
 * 유형(type)에 따라 지출(EXPENSE) 또는 저축(SAVING) 카테고리로 구분됩니다.
 */
@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Category extends BaseEntity {

    /**
     * 카테고리 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 카테고리 이름 (예: 식비, 교통비, 적금 등)
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * 카테고리 유형 (EXPENSE: 지출, SAVING: 저축)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private CategoryType type;

    /**
     * 해당 카테고리에 속한 월별 계획 목록
     */
    @Builder.Default
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Plan> plans = new ArrayList<>();
}
