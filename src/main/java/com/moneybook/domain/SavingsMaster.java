package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 적금 마스터 엔티티
 * 구성원이 가입한 적금 상품의 기본 정보(상품명, 은행, 월 납입액, 만기일, 금리)를 관리합니다.
 * 상품 단위의 마스터 정보로, 월별 잔액(SavingsAccount)과 1:N 관계입니다.
 */
@Entity
@Table(name = "savings_master")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SavingsMaster extends BaseEntity {

    /**
     * 적금 마스터 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 적금 상품명 (예: 청년 우대형 적금)
     */
    @Column(nullable = false, length = 100, columnDefinition = "VARCHAR(100) COMMENT '적금 상품명'")
    private String name;

    /**
     * 가입 은행
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", nullable = false, columnDefinition = "BIGINT COMMENT '은행 마스터 FK'")
    private BankMaster bankMaster;

    /**
     * 매월 납입하는 적금 금액 (원 단위)
     */
    @Column(name = "monthly_amount", nullable = false, precision = 15, scale = 2, columnDefinition = "DECIMAL(15, 2) COMMENT '월 납입액 (원 단위)'")
    private BigDecimal monthlyAmount;

    /**
     * 적금 시작 날짜
     */
    @Column(name = "start_date", columnDefinition = "DATE COMMENT '적금 시작일'")
    private LocalDate startDate;

    /**
     * 적금 만기 날짜
     */
    @Column(name = "maturity_date", columnDefinition = "DATE COMMENT '적금 만기일'")
    private LocalDate maturityDate;

    /**
     * 적금 금리 (%, 예: 3.50)
     */
    @Column(name = "interest_rate", precision = 5, scale = 2, columnDefinition = "DECIMAL(5, 2) COMMENT '금리 (%)'")
    private BigDecimal interestRate;

    /**
     * 예상 이자 (원 단위)
     */
    @Column(name = "expected_interest", precision = 15, scale = 2, columnDefinition = "DECIMAL(15, 2) COMMENT '예상 이자 (원 단위)'")
    private BigDecimal expectedInterest;

    /**
     * 실제 받은 이자 (원 단위)
     */
    @Column(name = "actual_interest", precision = 15, scale = 2, columnDefinition = "DECIMAL(15, 2) COMMENT '실제 받은 이자 (원 단위)'")
    private BigDecimal actualInterest;

    /**
     * 이 적금 상품을 보유한 구성원
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    /**
     * 월별 잔액 내역 목록
     */
    @Builder.Default
    @OneToMany(mappedBy = "savingsMaster", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SavingsAccount> savingsAccounts = new ArrayList<>();

    /**
     * 적금 마스터 정보를 수정합니다.
     *
     * @param name          수정할 상품명
     * @param bank          수정할 은행명
     * @param monthlyAmount 수정할 월 납입액
     * @param maturityDate  수정할 만기일
     * @param interestRate  수정할 금리
     */
    public void update(String name, BankMaster bankMaster, BigDecimal monthlyAmount, LocalDate startDate, LocalDate maturityDate, BigDecimal interestRate, BigDecimal expectedInterest, BigDecimal actualInterest) {
        this.name = name;
        this.bankMaster = bankMaster;
        this.monthlyAmount = monthlyAmount;
        this.startDate = startDate;
        this.maturityDate = maturityDate;
        this.interestRate = interestRate;
        this.expectedInterest = expectedInterest;
        this.actualInterest = actualInterest;
    }
}
