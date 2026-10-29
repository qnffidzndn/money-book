package com.moneybook.controller;

import com.moneybook.domain.BankMaster;
import com.moneybook.dto.request.BankMasterSaveRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.BankMasterResponse;
import com.moneybook.service.BankMasterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 은행 마스터 컨트롤러
 * 은행 코드/이름 조회 및 등록 REST API를 제공합니다.
 */
@Tag(name = "은행 마스터", description = "은행 코드 및 이름 관리 API")
@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankMasterController {

    private final BankMasterService bankMasterService;

    @Operation(summary = "은행 목록 조회", description = "전체 은행 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<BankMasterResponse>> getAllBanks() {
        List<BankMasterResponse> response = bankMasterService.findAll().stream()
                .map(BankMasterResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "은행 등록", description = "새 은행을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BankMasterResponse> createBank(@Valid @RequestBody BankMasterSaveRequest request) {
        BankMaster bank = BankMaster.builder()
                .code(request.getCode())
                .name(request.getName())
                .build();
        return ApiResponse.success("은행이 등록되었습니다.", BankMasterResponse.from(bankMasterService.save(bank)));
    }
}
