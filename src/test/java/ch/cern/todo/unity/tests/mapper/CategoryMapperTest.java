package ch.cern.todo.unity.tests.mapper;

import ch.cern.todo.dto.CategoryDto;
import ch.cern.todo.mapper.CategoryMapper;
import ch.cern.todo.model.Category;
import ch.cern.todo.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CategoryMapperTest {

    @InjectMocks
    private CategoryMapper categoryMapper;

    @Test
    public void testMapCategoryDtoToCategory() {
        // GIVEN
        CategoryDto categoryDto = TestUtils.buildCategoryDto();

        //WHEN
        Category mappedCategory = categoryMapper.mapCategoryDtoToCategory(categoryDto);

        //THEN
        assertEquals(categoryDto.getName(), mappedCategory.getName());
        assertEquals(categoryDto.getDescription(), mappedCategory.getDescription());
    }

    @Test
    public void testMapCategoryDtoNullToCategory() {
        //WHEN
        Category mappedCategory = categoryMapper.mapCategoryDtoToCategory(null);

        //THEN
        assertNull(mappedCategory);
    }

    @Test
    public void testMapCategoryToCategoryDto() {
        // GIVEN
        Category category = TestUtils.buildCategory();

        //WHEN
        CategoryDto mappedCategoryDto = categoryMapper.mapCategoryToCategoryDto(category);

        //THEN
        assertEquals(category.getName(), mappedCategoryDto.getName());
        assertEquals(category.getDescription(), mappedCategoryDto.getDescription());
    }

    @Test
    public void testMapCategoryNullToCategoryDto() {
        //WHEN
        CategoryDto mappedCategoryDto = categoryMapper.mapCategoryToCategoryDto(null);

        //THEN
        assertNull(mappedCategoryDto);
    }


}
