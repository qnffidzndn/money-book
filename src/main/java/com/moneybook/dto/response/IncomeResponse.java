package com.moneybook.dto.response;

import com.moneybook.domain.Income;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 월급 응답 DTO
 * 월급 내역 조회 API의 응답 데이터를 담습니다.
 */
@Getter
@Builder
public class IncomeResponse {

    /** 월급 내역 고유 식별자 */
    private Long id;

    /** 구성원 ID */
    private Long memberId;

    /** 구성원 이름 */
    private String memberName;

    /** 연월 (형식: yyyy-MM) */
    private String yearMonth;

    /** 월급 금액 */
    private BigDecimal amount;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    /**
     * Income 엔티티로부터 응답 DTO를 생성합니다.
     *
     * @param income 변환할 Income 엔티티
     * @return IncomeResponse DTO
     */
    public static IncomeResponse from(Income income) {
        return IncomeResponse.builder()
                .id(income.getId())
                .memberId(income.getMember().getId())
                .memberName(income.getMember().getName())
                .yearMonth(income.getYearMonth())
                .amount(income.getAmount())
                .createdAt(income.getCreatedAt())
                .build();
    }
}
