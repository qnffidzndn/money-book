package com.moneybook.controller;

import com.moneybook.domain.Member;
import com.moneybook.domain.SavingsAccount;
import com.moneybook.dto.request.SavingsAccountSaveRequest;
import com.moneybook.dto.request.SavingsAccountUpdateRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.SavingsAccountResponse;
import com.moneybook.service.MemberService;
import com.moneybook.service.SavingsAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 적금 상품 컨트롤러
 * 적금 상품 조회, 생성, 수정 REST API를 제공합니다.
 */
@Tag(name = "적금 상품", description = "구성원별 적금 상품 관리 API")
@RestController
@RequestMapping("/api/savings-accounts")
@RequiredArgsConstructor
public class SavingsAccountController {

    private final SavingsAccountService savingsAccountService;
    private final MemberService memberService;

    @Operation(summary = "전체 적금 상품 목록 조회", description = "전체 적금 상품 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<SavingsAccountResponse>> getAllSavingsAccounts() {
        List<SavingsAccountResponse> response = savingsAccountService.findAll().stream()
                .map(SavingsAccountResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "구성원별 적금 상품 목록 조회", description = "특정 구성원의 전체 적금 상품 목록을 조회합니다.")
    @GetMapping(params = "memberId")
    public ApiResponse<List<SavingsAccountResponse>> getSavingsAccounts(@RequestParam Long memberId) {
        List<SavingsAccountResponse> response = savingsAccountService.findByMember(memberId).stream()
                .map(SavingsAccountResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "적금 상품 생성", description = "새 적금 상품을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SavingsAccountResponse> createSavingsAccount(
            @Valid @RequestBody SavingsAccountSaveRequest request) {
        Member member = memberService.findById(request.getMemberId());
        SavingsAccount account = SavingsAccount.builder()
                .name(request.getName())
                .bank(request.getBank())
                .monthlyAmount(request.getMonthlyAmount())
                .maturityDate(request.getMaturityDate())
                .member(member)
                .build();
        return ApiResponse.success("적금 상품이 생성되었습니다.",
                SavingsAccountResponse.from(savingsAccountService.save(account)));
    }

    @Operation(summary = "적금 상품 수정", description = "적금 상품의 이름, 은행, 월 납입액, 만기일을 수정합니다.")
    @PutMapping("/{id}")
    public ApiResponse<SavingsAccountResponse> updateSavingsAccount(
            @PathVariable Long id,
            @Valid @RequestBody SavingsAccountUpdateRequest request) {
        SavingsAccount updated = savingsAccountService.update(
                id, request.getName(), request.getBank(),
                request.getMonthlyAmount(), request.getMaturityDate());
        return ApiResponse.success(SavingsAccountResponse.from(updated));
    }
}
