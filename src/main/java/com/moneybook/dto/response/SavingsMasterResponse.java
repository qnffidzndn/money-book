package com.moneybook.dto.response;

import com.moneybook.domain.SavingsMaster;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 적금 마스터 응답 DTO
 * 적금 마스터 조회 API의 응답 데이터를 담습니다.
 */
@Getter
@Builder
public class SavingsMasterResponse {

    /** 적금 마스터 고유 식별자 */
    private Long id;

    /** 적금 상품명 */
    private String name;

    /** 은행 ID */
    private Long bankId;

    /** 은행 코드 */
    private String bankCode;

    /** 은행명 */
    private String bankName;

    /** 월 납입액 */
    private BigDecimal monthlyAmount;

    /** 시작일 */
    private LocalDate startDate;

    /** 만기일 */
    private LocalDate maturityDate;

    /** 금리 (%) */
    private BigDecimal interestRate;

    /** 예상 이자 */
    private BigDecimal expectedInterest;

    /** 실제 받은 이자 */
    private BigDecimal actualInterest;

    /** 구성원 ID */
    private Long memberId;

    /** 구성원 이름 */
    private String memberName;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    public static SavingsMasterResponse from(SavingsMaster savingsMaster) {
        return SavingsMasterResponse.builder()
                .id(savingsMaster.getId())
                .name(savingsMaster.getName())
                .bankId(savingsMaster.getBankMaster().getId())
                .bankCode(savingsMaster.getBankMaster().getCode())
                .bankName(savingsMaster.getBankMaster().getName())
                .monthlyAmount(savingsMaster.getMonthlyAmount())
                .startDate(savingsMaster.getStartDate())
                .maturityDate(savingsMaster.getMaturityDate())
                .interestRate(savingsMaster.getInterestRate())
                .expectedInterest(savingsMaster.getExpectedInterest())
                .actualInterest(savingsMaster.getActualInterest())
                .memberId(savingsMaster.getMember().getId())
                .memberName(savingsMaster.getMember().getName())
                .createdAt(savingsMaster.getCreatedAt())
                .build();
    }
}
