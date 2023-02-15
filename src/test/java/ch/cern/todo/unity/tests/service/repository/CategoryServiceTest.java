package ch.cern.todo.unity.tests.service.repository;

import ch.cern.todo.model.Category;
import ch.cern.todo.repository.CategoryRepository;
import ch.cern.todo.service.repository.CategoryService;
import ch.cern.todo.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void testAddCategory() {
        // GIVEN
        Category category = TestUtils.buildCategory();

        Category returnedCategory = TestUtils.buildCategory();
        returnedCategory.setId(1L);

        //WHEN
        Mockito.when(categoryRepository.save(category)).thenReturn(returnedCategory);

        Category categoryResult = categoryService.addCategory(category);

        //THEN
        Mockito.verify(categoryRepository).save(category);
        assertEquals(category.getName(), categoryResult.getName());
        assertEquals(category.getDescription(), categoryResult.getDescription());
        assertTrue(categoryResult.getId() == 1L);
    }

    @Test
    public void testGetCategoryByIdReturnValue() {
        // GIVEN
        Long id = 1L;

        Category returnedCategory = TestUtils.buildCategory();
        returnedCategory.setId(1L);

        //WHEN
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.of(returnedCategory));

        Category categoryResult = categoryService.getCategoryById(id).get();

        //THEN
        Mockito.verify(categoryRepository).findById(id);
        assertEquals(returnedCategory.getId(), categoryResult.getId());
        assertEquals(returnedCategory.getName(), categoryResult.getName());
        assertEquals(returnedCategory.getDescription(), categoryResult.getDescription());
    }

    @Test
    public void testGetCategoryByIdReturnEmpty() {
        // GIVEN
        Long id = 1L;

        Category returnedCategory = TestUtils.buildCategory();
        returnedCategory.setId(1L);

        //WHEN
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Category> CategoryResult = categoryService.getCategoryById(id);

        //THEN
        Mockito.verify(categoryRepository).findById(id);
        assertEquals(Optional.empty(), CategoryResult);
    }

    @Test
    public void testGetAllCategoriesReturnValue() {
        // GIVEN
        Collection<Category> returnedCategories = TestUtils.buildCollectionCategory();

        //WHEN
        when(categoryRepository.findAll()).thenReturn((List<Category>) returnedCategories);

        Collection<Category> categoryResult = categoryService.getAllCategories();

        //THEN
        verify(categoryRepository).findAll();
        assertEquals(3, categoryResult.size());
    }

    @Test
    public void testGetAllCategoriesReturnEmpty() {
        //WHEN
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        Collection<Category> CategoryResult = categoryService.getAllCategories();

        //THEN
        verify(categoryRepository).findAll();
        assertEquals(Collections.EMPTY_LIST, CategoryResult);
    }

    @Test
    public void testUpdateCategory() {
        // GIVEN
        Category category = TestUtils.buildCategory();
        category.setId(1L);

        Category returnedCategory = TestUtils.buildCategory();
        returnedCategory.setId(1L);

        //WHEN
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(returnedCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(returnedCategory);

        Category categoryResult = categoryService.updateCategory(category).get();

        //THEN
        verify(categoryRepository).save(any(Category.class));
        assertEquals(returnedCategory.getId(), categoryResult.getId());
        assertEquals(returnedCategory.getName(), categoryResult.getName());
        assertEquals(returnedCategory.getDescription(), categoryResult.getDescription());
    }

    @Test
    public void testUpdateCategoryEmpty() {
        // GIVEN
        Category category = TestUtils.buildCategory();
        category.setId(1L);

        Category returnedCategory = TestUtils.buildCategory();
        returnedCategory.setId(1L);

        //WHEN
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Category> optionalCategoryResult = categoryService.updateCategory(category);

        //THEN
        verify(categoryRepository).findById(1L);
        assertEquals(Optional.empty(), optionalCategoryResult);
    }


}
