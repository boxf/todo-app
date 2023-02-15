package ch.cern.todo.service.rest;

import ch.cern.todo.dto.CategoryDto;
import ch.cern.todo.mapper.CategoryMapper;
import ch.cern.todo.model.Category;
import ch.cern.todo.service.repository.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Orchestration REST service used to manage {@link Category} for the TODOapp
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
    // TODO : verify the architecture. It does not seem to be really well implemented
    public Optional<CategoryDto> getCategoryById(Long id) {
        if (categoryService.getCategoryById(id).isPresent()) {
            return Optional.of(categoryMapper.mapCategoryToCategoryDto(categoryService.getCategoryById(id).get()));

        } else {
            return Optional.empty();
        }

    }


}
