package com.moneybook.repository;

import com.moneybook.domain.Category;
import com.moneybook.domain.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 카테고리 레포지토리
 * 지출 및 저축 카테고리에 대한 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * 카테고리 유형으로 카테고리 목록을 조회합니다.
     *
     * @param type 카테고리 유형 (EXPENSE: 지출, SAVING: 저축)
     * @return 해당 유형의 카테고리 목록
     */
    List<Category> findByType(CategoryType type);

    /**
     * 카테고리 이름으로 카테고리를 조회합니다.
     *
     * @param name 카테고리 이름
     * @return 해당 이름의 카테고리 (없으면 empty)
     */
    Optional<Category> findByName(String name);
}
