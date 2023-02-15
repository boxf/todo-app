package ch.cern.todo.service.rest;

import ch.cern.todo.dto.CategoryDto;
import ch.cern.todo.mapper.CategoryMapper;
import ch.cern.todo.model.Category;
import ch.cern.todo.service.repository.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Orchestration REST service used to manage {@link Category} for the TODOApp
 */
@Service
public class CategoryRestService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * Adds a new {@link Category} to the DB
     *
     * @param categoryDto
     * @return
     */
    public CategoryDto addNewCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.mapCategoryDtoToCategory(categoryDto);
        return categoryMapper.mapCategoryToCategoryDto(categoryService.addCategory(category));
    }

    /**
     * Retrieve a {@link Category} in DB with the given id
     *
     * @param id
     * @return
     */
    public Optional<CategoryDto> getCategoryById(Long id) {
        if (categoryService.getCategoryById(id).isPresent()) {
            return Optional.of(categoryMapper.mapCategoryToCategoryDto(categoryService.getCategoryById(id).get()));

        } else return Optional.empty();
    }

    /**
     * Update the {@link Category} corresponding to the given {@link CategoryDto} with the new values
     *
     * @param categoryDto
     * @return
     */
    public Optional<CategoryDto> updateCategory(CategoryDto categoryDto) {
        Category mappedCategory = categoryMapper.mapCategoryDtoToCategory(categoryDto);

        Optional<Category> updatedCategory = categoryService.updateCategory(mappedCategory);

        return updatedCategory.map(category -> categoryMapper.mapCategoryToCategoryDto(category));

    }



}
