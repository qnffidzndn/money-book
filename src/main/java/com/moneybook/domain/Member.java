package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 구성원 엔티티
 * 가계부를 함께 관리하는 구성원(A, B) 정보를 저장합니다.
 */
@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Member extends BaseEntity {

    /**
     * 구성원 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 구성원 이름 (예: A, B 또는 실명)
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * 구성원의 월급 내역 목록
     */
    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Income> incomes = new ArrayList<>();

    /**
     * 구성원의 월별 지출/저축 계획 목록
     */
    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Plan> plans = new ArrayList<>();

    /**
     * 구성원의 적금 상품 목록
     */
    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<SavingsAccount> savingsAccounts = new ArrayList<>();
}
