package com.moneybook.service;

import com.moneybook.domain.Category;
import com.moneybook.domain.CategoryType;
import com.moneybook.exception.CategoryNotFoundException;
import com.moneybook.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 카테고리 서비스
 * 지출 및 저축 카테고리 조회, 저장 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * ID로 카테고리를 조회합니다.
     *
     * @param id 카테고리 ID
     * @return 해당 카테고리
     * @throws CategoryNotFoundException 카테고리가 존재하지 않을 경우
     */
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    /**
     * 전체 카테고리 목록을 조회합니다.
     *
     * @return 전체 카테고리 목록
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * 카테고리 유형으로 목록을 조회합니다.
     *
     * @param type 카테고리 유형 (EXPENSE: 지출, SAVING: 저축)
     * @return 해당 유형의 카테고리 목록
     */
    public List<Category> findByType(CategoryType type) {
        return categoryRepository.findByType(type);
    }

    /**
     * 카테고리를 저장합니다.
     *
     * @param category 저장할 카테고리 엔티티
     * @return 저장된 카테고리
     */
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
