package com.moneybook.dto.response;

import com.moneybook.domain.CategoryType;
import com.moneybook.domain.Plan;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 월별 계획 응답 DTO
 * 월별 계획 조회 API의 응답 데이터를 담습니다.
 */
@Getter
@Builder
public class PlanResponse {

    /** 계획 고유 식별자 */
    private Long id;

    /** 구성원 ID */
    private Long memberId;

    /** 카테고리 ID */
    private Long categoryId;

    /** 카테고리 이름 */
    private String categoryName;

    /** 카테고리 유형 */
    private CategoryType categoryType;

    /** 연월 (형식: yyyy-MM) */
    private String yearMonth;

    /** 계획 금액 */
    private BigDecimal plannedAmount;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    /**
     * Plan 엔티티로부터 응답 DTO를 생성합니다.
     *
     * @param plan 변환할 Plan 엔티티
     * @return PlanResponse DTO
     */
    public static PlanResponse from(Plan plan) {
        return PlanResponse.builder()
                .id(plan.getId())
                .memberId(plan.getMember().getId())
                .categoryId(plan.getCategory().getId())
                .categoryName(plan.getCategory().getName())
                .categoryType(plan.getCategory().getType())
                .yearMonth(plan.getYearMonth())
                .plannedAmount(plan.getPlannedAmount())
                .createdAt(plan.getCreatedAt())
                .build();
    }
}
