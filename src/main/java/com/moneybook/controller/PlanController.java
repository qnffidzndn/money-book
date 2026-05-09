package com.moneybook.controller;

import com.moneybook.domain.Category;
import com.moneybook.domain.Member;
import com.moneybook.domain.Plan;
import com.moneybook.dto.request.PlanSaveRequest;
import com.moneybook.dto.request.PlanUpdateRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.PlanResponse;
import com.moneybook.service.CategoryService;
import com.moneybook.service.MemberService;
import com.moneybook.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 월별 계획 컨트롤러
 * 월별 계획 조회, 생성, 수정, 삭제 REST API를 제공합니다.
 */
@Tag(name = "월별 계획", description = "구성원별 월별 지출/저축 계획 관리 API")
@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final MemberService memberService;
    private final CategoryService categoryService;

    @Operation(summary = "월별 계획 조회", description = "특정 구성원의 특정 연월 계획 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<PlanResponse>> getPlans(
            @RequestParam Long memberId,
            @RequestParam String ym) {
        List<PlanResponse> response = planService.findByMemberAndYm(memberId, ym).stream()
                .map(PlanResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "월별 계획 생성", description = "구성원의 특정 연월 카테고리별 계획을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PlanResponse> createPlan(@Valid @RequestBody PlanSaveRequest request) {
        Member member = memberService.findById(request.getMemberId());
        Category category = categoryService.findById(request.getCategoryId());
        Plan plan = Plan.builder()
                .member(member)
                .category(category)
                .yearMonth(request.getYearMonth())
                .plannedAmount(request.getPlannedAmount())
                .build();
        return ApiResponse.success("계획이 생성되었습니다.", PlanResponse.from(planService.save(plan)));
    }

    @Operation(summary = "월별 계획 수정", description = "기존 계획의 연월과 계획 금액을 수정합니다.")
    @PutMapping("/{id}")
    public ApiResponse<PlanResponse> updatePlan(
            @PathVariable Long id,
            @Valid @RequestBody PlanUpdateRequest request) {
        Plan updated = planService.update(id, request.getYearMonth(), request.getPlannedAmount());
        return ApiResponse.success(PlanResponse.from(updated));
    }

    @Operation(summary = "월별 계획 삭제", description = "계획을 삭제합니다. 연관된 실제 사용 내역도 함께 삭제됩니다.")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePlan(@PathVariable Long id) {
        planService.delete(id);
        return ApiResponse.success("계획이 삭제되었습니다.", null);
    }
}
