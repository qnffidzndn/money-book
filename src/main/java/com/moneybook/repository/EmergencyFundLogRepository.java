package com.moneybook.repository;

import com.moneybook.domain.EmergencyFundLog;
import com.moneybook.domain.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 비상금 입출금 내역 레포지토리
 * 비상금 계좌의 입출금 거래 이력에 대한 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface EmergencyFundLogRepository extends JpaRepository<EmergencyFundLog, Long> {

    /**
     * 거래 유형으로 비상금 내역을 조회합니다.
     *
     * @param type 거래 유형 (IN: 입금, OUT: 출금)
     * @return 해당 유형의 비상금 거래 내역 목록
     */
    List<EmergencyFundLog> findByType(TransactionType type);

    /**
     * 특정 기간 내의 비상금 거래 내역을 조회합니다.
     *
     * @param start 조회 시작 날짜 (포함)
     * @param end   조회 종료 날짜 (포함)
     * @return 해당 기간의 비상금 거래 내역 목록
     */
    List<EmergencyFundLog> findByDateBetween(LocalDate start, LocalDate end);
}
