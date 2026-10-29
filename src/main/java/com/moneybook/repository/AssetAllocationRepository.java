package com.moneybook.repository;

import com.moneybook.domain.AssetAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetAllocationRepository extends JpaRepository<AssetAllocation, Long> {

    List<AssetAllocation> findByMemberId(Long memberId);
}
