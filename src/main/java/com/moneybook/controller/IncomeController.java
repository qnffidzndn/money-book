package com.moneybook.controller;

import com.moneybook.domain.Income;
import com.moneybook.domain.Member;
import com.moneybook.dto.request.IncomeSaveRequest;
import com.moneybook.dto.request.IncomeUpdateRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.IncomeResponse;
import com.moneybook.service.IncomeService;
import com.moneybook.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 월급 컨트롤러
 * 월급 내역 조회, 생성, 수정, 삭제 REST API를 제공합니다.
 */
@Tag(name = "월급", description = "구성원별 월급 내역 관리 API")
@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;
    private final MemberService memberService;

    @Operation(summary = "월급 내역 조회", description = "특정 구성원의 특정 연월 월급 내역을 조회합니다.")
    @GetMapping
    public ApiResponse<List<IncomeResponse>> getIncomes(
            @RequestParam Long memberId,
            @RequestParam String ym) {
        List<IncomeResponse> response = incomeService.findByMemberAndYm(memberId, ym).stream()
                .map(IncomeResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "월급 내역 생성", description = "구성원의 특정 연월 월급 내역을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<IncomeResponse> createIncome(@Valid @RequestBody IncomeSaveRequest request) {
        Member member = memberService.findById(request.getMemberId());
        Income income = Income.builder()
                .member(member)
                .yearMonth(request.getYearMonth())
                .amount(request.getAmount())
                .build();
        return ApiResponse.success("월급 내역이 생성되었습니다.", IncomeResponse.from(incomeService.save(income)));
    }

    @Operation(summary = "월급 내역 수정", description = "기존 월급 내역의 연월과 금액을 수정합니다.")
    @PutMapping("/{id}")
    public ApiResponse<IncomeResponse> updateIncome(
            @PathVariable Long id,
            @Valid @RequestBody IncomeUpdateRequest request) {
        Income updated = incomeService.update(id, request.getYearMonth(), request.getAmount());
        return ApiResponse.success(IncomeResponse.from(updated));
    }

    @Operation(summary = "월급 내역 삭제", description = "월급 내역을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteIncome(@PathVariable Long id) {
        incomeService.delete(id);
        return ApiResponse.success("월급 내역이 삭제되었습니다.", null);
    }
}
