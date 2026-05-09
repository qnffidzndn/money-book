package com.moneybook.controller;

import com.moneybook.domain.ActualSpending;
import com.moneybook.domain.Plan;
import com.moneybook.dto.request.ActualSpendingSaveRequest;
import com.moneybook.dto.request.ActualSpendingUpdateRequest;
import com.moneybook.dto.response.ActualSpendingResponse;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.service.ActualSpendingService;
import com.moneybook.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 실제 사용 내역 컨트롤러
 * 실제 사용 내역 조회, 생성, 수정 REST API를 제공합니다.
 */
@Tag(name = "실제 사용 내역", description = "계획 대비 실제 지출/저축 내역 관리 API")
@RestController
@RequestMapping("/api/actual-spendings")
@RequiredArgsConstructor
public class ActualSpendingController {

    private final ActualSpendingService actualSpendingService;
    private final PlanService planService;

    @Operation(summary = "실제 사용 내역 조회", description = "특정 계획에 속한 실제 사용 내역 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<ActualSpendingResponse>> getSpendings(@RequestParam Long planId) {
        List<ActualSpendingResponse> response = actualSpendingService.findByPlan(planId).stream()
                .map(ActualSpendingResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "실제 사용 내역 생성", description = "계획에 실제 지출/저축 내역을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ActualSpendingResponse> createSpending(@Valid @RequestBody ActualSpendingSaveRequest request) {
        Plan plan = planService.findById(request.getPlanId());
        ActualSpending spending = ActualSpending.builder()
                .plan(plan)
                .actualAmount(request.getActualAmount())
                .memo(request.getMemo())
                .build();
        return ApiResponse.success("실제 사용 내역이 생성되었습니다.",
                ActualSpendingResponse.from(actualSpendingService.save(spending)));
    }

    @Operation(summary = "실제 사용 내역 수정", description = "실제 사용 금액과 메모를 수정합니다.")
    @PutMapping("/{id}")
    public ApiResponse<ActualSpendingResponse> updateSpending(
            @PathVariable Long id,
            @Valid @RequestBody ActualSpendingUpdateRequest request) {
        ActualSpending updated = actualSpendingService.update(id, request.getActualAmount(), request.getMemo());
        return ApiResponse.success(ActualSpendingResponse.from(updated));
    }
}
