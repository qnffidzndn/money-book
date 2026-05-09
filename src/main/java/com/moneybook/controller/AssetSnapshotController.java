package com.moneybook.controller;

import com.moneybook.domain.AssetSnapshot;
import com.moneybook.dto.request.AssetSnapshotSaveRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.AssetSnapshotResponse;
import com.moneybook.service.AssetSnapshotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 월별 총자산 스냅샷 컨트롤러
 * 총자산 스냅샷 조회 및 생성 REST API를 제공합니다.
 */
@Tag(name = "총자산 스냅샷", description = "월별 합산 총자산 스냅샷 관리 API")
@RestController
@RequestMapping("/api/asset-snapshots")
@RequiredArgsConstructor
public class AssetSnapshotController {

    private final AssetSnapshotService assetSnapshotService;

    @Operation(summary = "전체 총자산 스냅샷 조회", description = "전체 월별 총자산 스냅샷을 최신 연월 순으로 조회합니다.")
    @GetMapping
    public ApiResponse<List<AssetSnapshotResponse>> getSnapshots() {
        List<AssetSnapshotResponse> response = assetSnapshotService.findAll().stream()
                .map(AssetSnapshotResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "연월별 총자산 스냅샷 조회", description = "특정 연월의 총자산 스냅샷을 조회합니다.")
    @GetMapping(params = "ym")
    public ApiResponse<AssetSnapshotResponse> getSnapshotByYm(@RequestParam String ym) {
        AssetSnapshotResponse response = assetSnapshotService.findByYm(ym)
                .map(AssetSnapshotResponse::from)
                .orElse(null);
        return ApiResponse.success(response);
    }

    @Operation(summary = "총자산 스냅샷 생성", description = "특정 연월의 총자산 스냅샷을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AssetSnapshotResponse> createSnapshot(@Valid @RequestBody AssetSnapshotSaveRequest request) {
        AssetSnapshot snapshot = AssetSnapshot.builder()
                .yearMonth(request.getYearMonth())
                .totalAmount(request.getTotalAmount())
                .memo(request.getMemo())
                .build();
        return ApiResponse.success("총자산 스냅샷이 생성되었습니다.",
                AssetSnapshotResponse.from(assetSnapshotService.save(snapshot)));
    }
}
