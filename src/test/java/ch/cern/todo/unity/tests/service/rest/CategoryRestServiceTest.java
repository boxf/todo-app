package ch.cern.todo.unity.tests.service.rest;

import ch.cern.todo.dto.CategoryDto;
import ch.cern.todo.mapper.CategoryMapper;
import ch.cern.todo.model.Category;
import ch.cern.todo.service.repository.CategoryService;
import ch.cern.todo.service.rest.CategoryRestService;
import ch.cern.todo.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CategoryRestServiceTest {

    @InjectMocks
    private CategoryRestService categoryRestService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryMapper categoryMapper;

    @Test
    public void testAddNewCategory() {
        // GIVEN
        CategoryDto categoryDto = TestUtils.buildCategoryDto();

        Category category = TestUtils.buildCategory();

        Category returnedCategory = TestUtils.buildCategory();
        returnedCategory.setId(1L);

        CategoryDto returnedCategoryDto = TestUtils.buildCategoryDto();
        returnedCategoryDto.setId(1L);

        //WHEN
        Mockito.when(categoryMapper.mapCategoryDtoToCategory(categoryDto)).thenReturn(category);
        Mockito.when(categoryService.addCategory(category)).thenReturn(returnedCategory);
        Mockito.when(categoryMapper.mapCategoryToCategoryDto(returnedCategory)).thenReturn(returnedCategoryDto);

        CategoryDto categoryDtoResult = categoryRestService.addNewCategory(categoryDto);

        //THEN
        Mockito.verify(categoryMapper).mapCategoryDtoToCategory(categoryDto);
        Mockito.verify(categoryService).addCategory(category);
        assertEquals(category.getName(), categoryDtoResult.getName());
        assertEquals(category.getDescription(), categoryDtoResult.getDescription());
    }

    @Test
    public void testGetCategoryByIdReturnValue() {
        // GIVEN
        Long id = 1L;

        Category returnedCategory = TestUtils.buildCategory();
        returnedCategory.setId(1L);

        CategoryDto returnedCategoryDto = TestUtils.buildCategoryDto();
        returnedCategoryDto.setId(1L);

        //WHEN
        Mockito.when(categoryService.getCategoryById(id)).thenReturn(Optional.of(returnedCategory));
        Mockito.when(categoryMapper.mapCategoryToCategoryDto(returnedCategory)).thenReturn(returnedCategoryDto);

        CategoryDto categoryDtoResult = categoryRestService.getCategoryById(id).get();

        //THEN
        Mockito.verify(categoryMapper).mapCategoryToCategoryDto(returnedCategory);
        Mockito.verify(categoryService, Mockito.times(2)).getCategoryById(id);
        assertEquals(returnedCategory.getId(), categoryDtoResult.getId());
        assertEquals(returnedCategory.getName(), categoryDtoResult.getName());
        assertEquals(returnedCategory.getDescription(), categoryDtoResult.getDescription());
    }

    @Test
    public void testGetCategoryByIdReturnEmpty() {
        // GIVEN
        Long id = 1L;

        Category returnedCategory = TestUtils.buildCategory();
        returnedCategory.setId(1L);

        CategoryDto returnedCategoryDto = TestUtils.buildCategoryDto();
        returnedCategoryDto.setId(1L);

        //WHEN
        Mockito.when(categoryService.getCategoryById(id)).thenReturn(Optional.empty());

        Optional<CategoryDto> categoryDtoResult = categoryRestService.getCategoryById(id);

        //THEN
        Mockito.verify(categoryMapper, Mockito.never()).mapCategoryToCategoryDto(returnedCategory);
        Mockito.verify(categoryService).getCategoryById(id);
        assertEquals(Optional.empty(), categoryDtoResult);
    }


}
