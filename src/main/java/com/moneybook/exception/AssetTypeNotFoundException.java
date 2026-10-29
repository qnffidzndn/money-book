package com.moneybook.exception;

/**
 * 자산 유형 마스터를 찾을 수 없을 때 발생하는 예외
 */
public class AssetTypeNotFoundException extends BusinessException {

    public AssetTypeNotFoundException(Long id) {
        super(ErrorCode.ASSET_TYPE_NOT_FOUND, "해당 자산 유형을 찾을 수 없습니다. id=" + id);
    }
}
