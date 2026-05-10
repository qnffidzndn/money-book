package com.moneybook.controller;

import com.moneybook.domain.Income;
import com.moneybook.domain.Member;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.DashboardSummaryResponse;
import com.moneybook.service.ActualSpendingService;
import com.moneybook.service.IncomeService;
import com.moneybook.service.MemberService;
import com.moneybook.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * 대시보드 컨트롤러
 * 월별 수입·계획·실제 사용 내역 요약 정보를 제공합니다.
 */
@Tag(name = "대시보드", description = "월별 가계부 요약 정보 API")
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final MemberService memberService;
    private final IncomeService incomeService;
    private final PlanService planService;
    private final ActualSpendingService actualSpendingService;

    @Operation(summary = "월별 요약 조회", description = "특정 연월의 구성원별 수입, 계획, 실제 사용 금액 합계를 조회합니다.")
    @GetMapping("/summary")
    public ApiResponse<DashboardSummaryResponse> getSummary(
            @RequestParam int year,
            @RequestParam int month) {
        String ym = String.format("%d-%02d", year, month);
        List<Member> members = memberService.findAll();

        List<DashboardSummaryResponse.MemberSummary> memberSummaries = members.stream()
                .map(member -> {
                    BigDecimal totalIncome = incomeService.findByMemberAndYm(member.getId(), ym).stream()
                            .map(Income::getAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal totalPlanned = planService.getTotalPlannedByMemberAndYm(member.getId(), ym);
                    BigDecimal totalActual = actualSpendingService.getTotalActualByMemberAndYm(member.getId(), ym);

                    return DashboardSummaryResponse.MemberSummary.builder()
                            .memberId(member.getId())
                            .memberName(member.getName())
                            .totalIncome(totalIncome)
                            .totalPlanned(totalPlanned)
                            .totalActual(totalActual)
                            .build();
                })
                .toList();

        DashboardSummaryResponse response = DashboardSummaryResponse.builder()
                .ym(ym)
                .members(memberSummaries)
                .build();

        return ApiResponse.success(response);
    }
}
