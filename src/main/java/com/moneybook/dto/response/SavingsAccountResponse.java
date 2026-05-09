package com.moneybook.dto.response;

import com.moneybook.domain.SavingsAccount;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 적금 상품 응답 DTO
 * 적금 상품 조회 API의 응답 데이터를 담습니다.
 */
@Getter
@Builder
public class SavingsAccountResponse {

    /** 적금 상품 고유 식별자 */
    private Long id;

    /** 적금 상품명 */
    private String name;

    /** 은행명 */
    private String bank;

    /** 월 납입액 */
    private BigDecimal monthlyAmount;

    /** 만기일 */
    private LocalDate maturityDate;

    /** 구성원 ID */
    private Long memberId;

    /** 구성원 이름 */
    private String memberName;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    /**
     * SavingsAccount 엔티티로부터 응답 DTO를 생성합니다.
     *
     * @param account 변환할 SavingsAccount 엔티티
     * @return SavingsAccountResponse DTO
     */
    public static SavingsAccountResponse from(SavingsAccount account) {
        return SavingsAccountResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .bank(account.getBank())
                .monthlyAmount(account.getMonthlyAmount())
                .maturityDate(account.getMaturityDate())
                .memberId(account.getMember().getId())
                .memberName(account.getMember().getName())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
