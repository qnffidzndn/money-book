package com.moneybook.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 대시보드 요약 응답 DTO
 * 특정 연월의 구성원별 수입, 계획, 실제 사용 내역 합계를 담습니다.
 */
@Getter
@Builder
public class DashboardSummaryResponse {

    /** 조회 연월 (yyyy-MM) */
    private String ym;

    /** 구성원별 요약 목록 */
    private List<MemberSummary> members;

    @Getter
    @Builder
    public static class MemberSummary {

        /** 구성원 ID */
        private Long memberId;

        /** 구성원 이름 */
        private String memberName;

        /** 해당 월 총 수입 */
        private BigDecimal totalIncome;

        /** 해당 월 총 계획 금액 */
        private BigDecimal totalPlanned;

        /** 해당 월 총 실제 사용 금액 */
        private BigDecimal totalActual;
    }
}
