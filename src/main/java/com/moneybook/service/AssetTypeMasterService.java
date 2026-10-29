package com.moneybook.service;

import com.moneybook.domain.AssetTypeMaster;
import com.moneybook.exception.AssetTypeNotFoundException;
import com.moneybook.repository.AssetTypeMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 자산 유형 마스터 서비스
 * 자산 유형 조회, 저장, 수정, 삭제 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssetTypeMasterService {

    private final AssetTypeMasterRepository assetTypeMasterRepository;

    public List<AssetTypeMaster> findAll() {
        return assetTypeMasterRepository.findAll();
    }

    public AssetTypeMaster findById(Long id) {
        return assetTypeMasterRepository.findById(id)
                .orElseThrow(() -> new AssetTypeNotFoundException(id));
    }

    @Transactional
    public AssetTypeMaster save(AssetTypeMaster assetTypeMaster) {
        return assetTypeMasterRepository.save(assetTypeMaster);
    }

    @Transactional
    public AssetTypeMaster update(Long id, String name) {
        AssetTypeMaster assetType = assetTypeMasterRepository.findById(id)
                .orElseThrow(() -> new AssetTypeNotFoundException(id));
        assetType.update(name);
        return assetType;
    }

    @Transactional
    public void delete(Long id) {
        if (!assetTypeMasterRepository.existsById(id)) {
            throw new AssetTypeNotFoundException(id);
        }
        assetTypeMasterRepository.deleteById(id);
    }
}
