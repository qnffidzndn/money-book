package com.moneybook.service;

import com.moneybook.config.JwtUtil;
import com.moneybook.domain.Member;
import com.moneybook.dto.response.LoginResponse;
import com.moneybook.exception.BusinessException;
import com.moneybook.exception.ErrorCode;
import com.moneybook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 인증 서비스
 * 로그인 검증 및 JWT 토큰 발급을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(String username, String password) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT, "아이디 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_INPUT, "아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        String token = jwtUtil.generateToken(member.getId());

        return LoginResponse.builder()
                .token(token)
                .memberId(member.getId())
                .memberName(member.getName())
                .build();
    }
}
