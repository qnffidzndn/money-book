package com.moneybook.service;

import com.moneybook.domain.AssetSnapshot;
import com.moneybook.repository.AssetSnapshotRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * 월별 총자산 스냅샷 서비스
 * 월별 합산 총자산 스냅샷의 조회, 저장, 수정 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssetSnapshotService {

    private final AssetSnapshotRepository assetSnapshotRepository;

    /**
     * 특정 연월의 총자산 스냅샷을 조회합니다.
     *
     * @param yearMonth 조회할 연월 (형식: yyyy-MM)
     * @return 해당 연월의 총자산 스냅샷 (없으면 empty)
     */
    public Optional<AssetSnapshot> findByYm(String yearMonth) {
        return assetSnapshotRepository.findByYearMonth(yearMonth);
    }

    /**
     * 전체 총자산 스냅샷을 연월 내림차순으로 조회합니다.
     *
     * @return 연월 최신순으로 정렬된 총자산 스냅샷 목록
     */
    public List<AssetSnapshot> findAll() {
        return assetSnapshotRepository.findAllByOrderByYearMonthDesc();
    }

    /**
     * 가장 최근 연월의 총자산 스냅샷을 조회합니다.
     *
     * @return 최신 총자산 스냅샷 (없으면 empty)
     */
    public Optional<AssetSnapshot> getLatestSnapshot() {
        return assetSnapshotRepository.findAllByOrderByYearMonthDesc()
                .stream()
                .findFirst();
    }

    /**
     * 총자산 스냅샷을 저장합니다.
     *
     * @param assetSnapshot 저장할 총자산 스냅샷 엔티티
     * @return 저장된 총자산 스냅샷
     */
    @Transactional
    public AssetSnapshot save(AssetSnapshot assetSnapshot) {
        return assetSnapshotRepository.save(assetSnapshot);
    }

    /**
     * 총자산 금액과 메모를 수정합니다.
     *
     * @param id          수정할 총자산 스냅샷 ID
     * @param totalAmount 수정할 총자산 금액
     * @param memo        수정할 메모
     * @return 수정된 총자산 스냅샷
     * @throws EntityNotFoundException 총자산 스냅샷이 존재하지 않을 경우
     */
    @Transactional
    public AssetSnapshot update(Long id, BigDecimal totalAmount, String memo) {
        AssetSnapshot assetSnapshot = assetSnapshotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("총자산 스냅샷을 찾을 수 없습니다. id=" + id));
        assetSnapshot.update(totalAmount, memo);
        return assetSnapshot;
    }
}
