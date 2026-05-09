package com.moneybook.controller;

import com.moneybook.domain.Member;
import com.moneybook.dto.request.MemberSaveRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.MemberResponse;
import com.moneybook.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 구성원 컨트롤러
 * 구성원 조회 및 생성 REST API를 제공합니다.
 */
@Tag(name = "구성원", description = "구성원 관리 API")
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "전체 구성원 조회", description = "가계부를 함께 관리하는 전체 구성원 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<MemberResponse>> getMembers() {
        List<MemberResponse> response = memberService.findAll().stream()
                .map(MemberResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "구성원 단건 조회", description = "ID로 특정 구성원을 조회합니다.")
    @GetMapping("/{id}")
    public ApiResponse<MemberResponse> getMember(@PathVariable Long id) {
        return ApiResponse.success(MemberResponse.from(memberService.findById(id)));
    }

    @Operation(summary = "구성원 생성", description = "새 구성원을 생성합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberResponse> createMember(@Valid @RequestBody MemberSaveRequest request) {
        Member member = Member.builder()
                .name(request.getName())
                .build();
        return ApiResponse.success("구성원이 생성되었습니다.", MemberResponse.from(memberService.save(member)));
    }
}
