package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 적금 상품 엔티티
 * 구성원이 가입한 적금 상품의 기본 정보(상품명, 은행, 월 납입액, 만기일)를 관리합니다.
 */
@Entity
@Table(name = "savings_account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SavingsAccount extends BaseEntity {

    /**
     * 적금 상품 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 적금 상품명 (예: 청년 우대형 적금)
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * 적금 가입 은행명 (예: 카카오뱅크, 국민은행)
     */
    @Column(nullable = false, length = 100)
    private String bank;

    /**
     * 매월 납입하는 적금 금액 (원 단위)
     */
    @Column(name = "monthly_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal monthlyAmount;

    /**
     * 적금 만기 날짜
     */
    @Column(name = "maturity_date", nullable = false)
    private LocalDate maturityDate;

    /**
     * 이 적금 상품을 보유한 구성원
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    /**
     * 월별 적금 잔액 추이 목록
     */
    @Builder.Default
    @OneToMany(mappedBy = "savingsAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SavingsBalance> savingsBalances = new ArrayList<>();

    /**
     * 만기 후 자금 이동 내역 목록
     */
    @Builder.Default
    @OneToMany(mappedBy = "savingsAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SavingsTransfer> savingsTransfers = new ArrayList<>();

    /**
     * 적금 상품 정보를 수정합니다.
     *
     * @param name          수정할 상품명
     * @param bank          수정할 은행명
     * @param monthlyAmount 수정할 월 납입액
     * @param maturityDate  수정할 만기일
     */
    public void update(String name, String bank, BigDecimal monthlyAmount, LocalDate maturityDate) {
        this.name = name;
        this.bank = bank;
        this.monthlyAmount = monthlyAmount;
        this.maturityDate = maturityDate;
    }
}
