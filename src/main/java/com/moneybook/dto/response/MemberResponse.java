package com.moneybook.dto.response;

import com.moneybook.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 구성원 응답 DTO
 * 구성원 조회 API의 응답 데이터를 담습니다.
 */
@Getter
@Builder
public class MemberResponse {

    /** 구성원 고유 식별자 */
    private Long id;

    /** 구성원 이름 */
    private String name;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    /**
     * Member 엔티티로부터 응답 DTO를 생성합니다.
     *
     * @param member 변환할 Member 엔티티
     * @return MemberResponse DTO
     */
    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
