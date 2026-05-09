package com.moneybook.controller;

import com.moneybook.domain.EmergencyFundLog;
import com.moneybook.domain.TransactionType;
import com.moneybook.dto.request.EmergencyFundLogSaveRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.EmergencyFundLogResponse;
import com.moneybook.service.EmergencyFundLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 비상금 입출금 내역 컨트롤러
 * 비상금 거래 내역 조회 및 생성 REST API를 제공합니다.
 */
@Tag(name = "비상금", description = "비상금 입출금 거래 내역 관리 API")
@RestController
@RequestMapping("/api/emergency-fund-logs")
@RequiredArgsConstructor
public class EmergencyFundLogController {

    private final EmergencyFundLogService emergencyFundLogService;

    @Operation(summary = "유형별 비상금 내역 조회", description = "IN(입금) 또는 OUT(출금) 유형으로 비상금 거래 내역을 조회합니다.")
    @GetMapping(params = "type")
    public ApiResponse<List<EmergencyFundLogResponse>> getLogsByType(@RequestParam TransactionType type) {
        List<EmergencyFundLogResponse> response = emergencyFundLogService.findByType(type).stream()
                .map(EmergencyFundLogResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "기간별 비상금 내역 조회", description = "startDate ~ endDate 기간 내의 비상금 거래 내역을 조회합니다.")
    @GetMapping(params = {"startDate", "endDate"})
    public ApiResponse<List<EmergencyFundLogResponse>> getLogsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<EmergencyFundLogResponse> response = emergencyFundLogService.findByDateRange(startDate, endDate).stream()
                .map(EmergencyFundLogResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "비상금 내역 생성", description = "비상금 입금 또는 출금 내역을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<EmergencyFundLogResponse> createLog(@Valid @RequestBody EmergencyFundLogSaveRequest request) {
        EmergencyFundLog log = EmergencyFundLog.builder()
                .date(request.getDate())
                .amount(request.getAmount())
                .type(request.getType())
                .memo(request.getMemo())
                .build();
        return ApiResponse.success("비상금 내역이 생성되었습니다.",
                EmergencyFundLogResponse.from(emergencyFundLogService.save(log)));
    }
}
