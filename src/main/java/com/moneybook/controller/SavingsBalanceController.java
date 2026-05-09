package com.moneybook.controller;

import com.moneybook.domain.SavingsAccount;
import com.moneybook.domain.SavingsBalance;
import com.moneybook.dto.request.SavingsBalanceSaveRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.SavingsBalanceResponse;
import com.moneybook.service.SavingsAccountService;
import com.moneybook.service.SavingsBalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 적금 월별 잔액 컨트롤러
 * 적금 월별 잔액 조회 및 생성 REST API를 제공합니다.
 */
@Tag(name = "적금 잔액", description = "적금 상품 월별 잔액 추이 관리 API")
@RestController
@RequestMapping("/api/savings-balances")
@RequiredArgsConstructor
public class SavingsBalanceController {

    private final SavingsBalanceService savingsBalanceService;
    private final SavingsAccountService savingsAccountService;

    @Operation(summary = "적금 월별 잔액 조회", description = "특정 적금 상품의 특정 연월 잔액을 조회합니다.")
    @GetMapping
    public ApiResponse<SavingsBalanceResponse> getBalance(
            @RequestParam Long accountId,
            @RequestParam String ym) {
        SavingsBalanceResponse response = savingsBalanceService.findByAccountAndYm(accountId, ym)
                .map(SavingsBalanceResponse::from)
                .orElse(null);
        return ApiResponse.success(response);
    }

    @Operation(summary = "적금 월별 잔액 생성", description = "특정 적금 상품의 월별 잔액을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SavingsBalanceResponse> createBalance(@Valid @RequestBody SavingsBalanceSaveRequest request) {
        SavingsAccount account = savingsAccountService.findById(request.getSavingsAccountId());
        SavingsBalance balance = SavingsBalance.builder()
                .savingsAccount(account)
                .yearMonth(request.getYearMonth())
                .balance(request.getBalance())
                .build();
        return ApiResponse.success("적금 잔액이 저장되었습니다.",
                SavingsBalanceResponse.from(savingsBalanceService.save(balance)));
    }
}
