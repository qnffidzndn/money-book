package com.moneybook.exception;

/**
 * 자산 항목을 찾을 수 없을 때 발생하는 예외
 */
public class AssetAllocationNotFoundException extends BusinessException {

    public AssetAllocationNotFoundException(Long id) {
        super(ErrorCode.ASSET_ALLOCATION_NOT_FOUND, "해당 자산 항목을 찾을 수 없습니다. id=" + id);
    }
}
