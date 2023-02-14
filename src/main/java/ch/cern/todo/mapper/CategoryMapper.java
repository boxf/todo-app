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
    public Category mapCategoryDtoToCategory(CategoryDto categoryDto) {
        if (categoryDto != null) {
            Category category = new Category();
            category.setId(categoryDto.getId());
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());

            return category;

        } else {
            return null;
        }

    }

    /**
     * Maps a Category to a CategoryDto
     *
     * @param category
     * @return categoryDto
     */
    public CategoryDto mapCategoryToCategoryDto(Category category) {
        if (category != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());

            return categoryDto;

        } else {
            return null;
        }
    }
}
