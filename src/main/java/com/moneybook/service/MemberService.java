package com.moneybook.service;

import com.moneybook.domain.Member;
import com.moneybook.exception.MemberNotFoundException;
import com.moneybook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 구성원 서비스
 * 구성원 조회 및 저장 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 전체 구성원 목록을 조회합니다.
     *
     * @return 전체 구성원 목록
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    /**
     * ID로 구성원을 조회합니다.
     *
     * @param id 구성원 ID
     * @return 해당 구성원
     * @throws MemberNotFoundException 구성원이 존재하지 않을 경우
     */
    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    /**
     * 구성원을 저장합니다.
     *
     * @param member 저장할 구성원 엔티티
     * @return 저장된 구성원
     */
    @Transactional
    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
