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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


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

    @Test
    public void testGetAllCategoryReturnValue() {
        // GIVEN
        Collection<Category> returnedCategories = TestUtils.buildCollectionCategory();

        CategoryDto returnedCategoryDto = TestUtils.buildCategoryDto();
        returnedCategoryDto.setId(1L);

        //WHEN
        when(categoryService.getAllCategories()).thenReturn((returnedCategories));
        when(categoryMapper.mapCategoryToCategoryDto(any())).thenReturn(returnedCategoryDto);

        Collection<CategoryDto> CategoriesDtoResult = categoryRestService.getAllCategories();

        //THEN
        verify(categoryMapper, times(3)).mapCategoryToCategoryDto(any());
        verify(categoryService).getAllCategories();
        assertEquals(3, CategoriesDtoResult.size());
    }

    @Test
    public void testGetAllCategoryReturnEmpty() {
        // GIVEN
        Category returnedCategory = TestUtils.buildCategory();
        returnedCategory.setId(1L);

        CategoryDto returnedCategoryDto = TestUtils.buildCategoryDto();
        returnedCategoryDto.setId(1L);

        //WHEN
        when(categoryService.getAllCategories()).thenReturn(Collections.emptyList());

        Collection<CategoryDto> CategoryDtoResult = categoryRestService.getAllCategories();

        //THEN
        verify(categoryMapper, never()).mapCategoryToCategoryDto(returnedCategory);
        verify(categoryService).getAllCategories();
        assertEquals(Collections.EMPTY_LIST, CategoryDtoResult);
    }

    @Test
    public void testUpdateCategoryReturnValue() {
        // GIVEN
        CategoryDto categoryDto = TestUtils.buildCategoryDto();
        categoryDto.setId(1L);

        Category mappedCategory = TestUtils.buildCategory();
        mappedCategory.setId(1L);

        Category returnedCategory = TestUtils.buildCategory();
        returnedCategory.setId(1L);

        CategoryDto returnedCategoryDto = TestUtils.buildCategoryDto();
        returnedCategoryDto.setId(1L);

        //WHEN
        when(categoryMapper.mapCategoryDtoToCategory(categoryDto)).thenReturn(mappedCategory);
        when(categoryService.updateCategory(mappedCategory)).thenReturn(of(returnedCategory));
        when(categoryMapper.mapCategoryToCategoryDto(returnedCategory)).thenReturn(returnedCategoryDto);

        CategoryDto CategoryDtoResult = categoryRestService.updateCategory(categoryDto).get();

        //THEN
        verify(categoryMapper).mapCategoryDtoToCategory(categoryDto);
        verify(categoryMapper).mapCategoryToCategoryDto(returnedCategory);
        verify(categoryService).updateCategory(mappedCategory);
        assertEquals(returnedCategory.getId(), CategoryDtoResult.getId());
        assertEquals(returnedCategory.getName(), CategoryDtoResult.getName());
        assertEquals(returnedCategory.getDescription(), CategoryDtoResult.getDescription());
    }

    @Test
    public void testUpdateCategoryReturnEmpty() {
        // GIVEN
        CategoryDto categoryDto = TestUtils.buildCategoryDto();
        categoryDto.setId(1L);

        Category mappedCategory = TestUtils.buildCategory();
        mappedCategory.setId(1L);

        Category returnedCategory = TestUtils.buildCategory();
        returnedCategory.setId(1L);

        //WHEN
        when(categoryMapper.mapCategoryDtoToCategory(categoryDto)).thenReturn(mappedCategory);
        when(categoryService.updateCategory(mappedCategory)).thenReturn(empty());

        Optional<CategoryDto> CategoryDtoResult = categoryRestService.updateCategory(categoryDto);

        //THEN
        verify(categoryMapper).mapCategoryDtoToCategory(categoryDto);
        verify(categoryService).updateCategory(mappedCategory);
        verify(categoryMapper, never()).mapCategoryToCategoryDto(returnedCategory);
        assertEquals(empty(), CategoryDtoResult);
    }


}
