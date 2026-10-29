package com.moneybook.service;

import com.moneybook.domain.AssetAllocation;
import com.moneybook.domain.AssetTypeMaster;
import com.moneybook.exception.AssetAllocationNotFoundException;
import com.moneybook.repository.AssetAllocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 자산 배분 서비스
 * 자산 항목 조회, 저장, 수정, 삭제 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssetAllocationService {

    private final AssetAllocationRepository assetAllocationRepository;

    public List<AssetAllocation> findByMember(Long memberId) {
        return assetAllocationRepository.findByMemberId(memberId);
    }

    public AssetAllocation findById(Long id) {
        return assetAllocationRepository.findById(id)
                .orElseThrow(() -> new AssetAllocationNotFoundException(id));
    }

    @Transactional
    public AssetAllocation save(AssetAllocation assetAllocation) {
        return assetAllocationRepository.save(assetAllocation);
    }

    @Transactional
    public AssetAllocation update(Long id, LocalDate assetDate, AssetTypeMaster assetType, BigDecimal amount, String description) {
        AssetAllocation asset = assetAllocationRepository.findById(id)
                .orElseThrow(() -> new AssetAllocationNotFoundException(id));
        asset.update(assetDate, assetType, amount, description);
        return asset;
    }

    @Transactional
    public void delete(Long id) {
        if (!assetAllocationRepository.existsById(id)) {
            throw new AssetAllocationNotFoundException(id);
        }
        assetAllocationRepository.deleteById(id);
    }
}
