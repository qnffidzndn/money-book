package com.moneybook.repository;

import com.moneybook.domain.AssetSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 월별 총자산 스냅샷 레포지토리
 * 월별 합산 총자산 스냅샷에 대한 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface AssetSnapshotRepository extends JpaRepository<AssetSnapshot, Long> {

    /**
     * 특정 연월의 총자산 스냅샷을 조회합니다.
     *
     * @param yearMonth 조회할 연월 (형식: yyyy-MM)
     * @return 해당 연월의 총자산 스냅샷 (없으면 empty)
     */
    Optional<AssetSnapshot> findByYearMonth(String yearMonth);

    /**
     * 전체 총자산 스냅샷을 연월 내림차순으로 조회합니다.
     *
     * @return 연월 최신순으로 정렬된 총자산 스냅샷 목록
     */
    List<AssetSnapshot> findAllByOrderByYearMonthDesc();
}
