package com.moneybook.service;

import com.moneybook.domain.EmergencyFundLog;
import com.moneybook.domain.TransactionType;
import com.moneybook.repository.EmergencyFundLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 비상금 입출금 서비스
 * 비상금 계좌의 입출금 거래 이력 조회 및 저장 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmergencyFundLogService {

    private final EmergencyFundLogRepository emergencyFundLogRepository;

    /**
     * 특정 기간 내의 비상금 거래 내역을 조회합니다.
     *
     * @param start 조회 시작 날짜 (포함)
     * @param end   조회 종료 날짜 (포함)
     * @return 해당 기간의 비상금 거래 내역 목록
     */
    public List<EmergencyFundLog> findByDateRange(LocalDate start, LocalDate end) {
        return emergencyFundLogRepository.findByDateBetween(start, end);
    }

    /**
     * 거래 유형으로 비상금 내역을 조회합니다.
     *
     * @param type 거래 유형 (IN: 입금, OUT: 출금)
     * @return 해당 유형의 비상금 거래 내역 목록
     */
    public List<EmergencyFundLog> findByType(TransactionType type) {
        return emergencyFundLogRepository.findByType(type);
    }

    /**
     * 비상금 거래 내역을 저장합니다.
     *
     * @param emergencyFundLog 저장할 비상금 거래 엔티티
     * @return 저장된 비상금 거래 내역
     */
    @Transactional
    public EmergencyFundLog save(EmergencyFundLog emergencyFundLog) {
        return emergencyFundLogRepository.save(emergencyFundLog);
    }
}
