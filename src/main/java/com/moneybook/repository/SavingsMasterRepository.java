package com.moneybook.repository;

import com.moneybook.domain.SavingsMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 적금 마스터 레포지토리
 * 적금 상품 마스터 정보에 대한 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface SavingsMasterRepository extends JpaRepository<SavingsMaster, Long> {

    /**
     * 특정 구성원의 전체 적금 마스터 목록을 조회합니다.
     *
     * @param memberId 구성원 ID
     * @return 해당 구성원의 적금 마스터 목록
     */
    List<SavingsMaster> findByMemberId(Long memberId);
}
