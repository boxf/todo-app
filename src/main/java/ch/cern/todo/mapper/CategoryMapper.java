package ch.cern.todo.mapper;

import ch.cern.todo.dto.CategoryDto;
import ch.cern.todo.model.Category;
import org.springframework.stereotype.Service;

/**
 * TODO : verifier le type du bean à mettre
 */
@Service
public class CategoryMapper {

    /**
     * Maps a CategoryDtoIn to a Category
     *
     * @param categoryDto
     * @return category
     */
    public Category mapCategoryDtoInToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return category;
    }

    /**
     * Maps a Category to a CategoryDto
     *
     * @param category
     * @return categoryDto
     */
    public CategoryDto mapCategoryToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());

        return categoryDto;
    }
}
