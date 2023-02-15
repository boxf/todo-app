package ch.cern.todo.unity.tests.controller;

import ch.cern.todo.controller.CategoryRestController;
import ch.cern.todo.dto.CategoryDto;
import ch.cern.todo.service.rest.CategoryRestService;
import ch.cern.todo.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CategoryRestControllerTest {

    @InjectMocks
    private CategoryRestController categoryRestController;

    @Mock
    private CategoryRestService categoryRestService;

    @Test
    public void testPostCategory() {
        // GIVEN
        CategoryDto categoryDto = TestUtils.buildCategoryDto();

        //WHEN
        Mockito.when(categoryRestService.addNewCategory(categoryDto)).thenReturn(null);
        ResponseEntity<Void> response = categoryRestController.postCategory(categoryDto);

        //THEN
        Mockito.verify(categoryRestService).addNewCategory(categoryDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCategoryFound() {
        // GIVEN
        Long id = 1L;

        Optional<CategoryDto> optCategoryDto = Optional.of(TestUtils.buildCategoryDto());

        //WHEN
        Mockito.when(categoryRestService.getCategoryById(id)).thenReturn(optCategoryDto);
        ResponseEntity<CategoryDto> response = categoryRestController.getCategory(id);

        //THEN
        Mockito.verify(categoryRestService).getCategoryById(id);
        assertEquals(optCategoryDto.get(), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCategoryNotFound() {
        // GIVEN
        Long id = 1L;

        //WHEN
        Mockito.when(categoryRestService.getCategoryById(id)).thenReturn(Optional.empty());
        ResponseEntity<CategoryDto> response = categoryRestController.getCategory(id);

        //THEN
        Mockito.verify(categoryRestService).getCategoryById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllCategoryFound() {
        // GIVEN
        Collection<CategoryDto> categoriesDto = TestUtils.buildCollectionCategoryDto();

        //WHEN
        Mockito.when(categoryRestService.getAllCategories()).thenReturn(categoriesDto);
        ResponseEntity<Collection<CategoryDto>> response = categoryRestController.getAllCategories();

        //THEN
        Mockito.verify(categoryRestService).getAllCategories();
        assertEquals(3, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetAllCategoryEmpty() {
        //WHEN
        Mockito.when(categoryRestService.getAllCategories()).thenReturn(Collections.emptyList());
        ResponseEntity<Collection<CategoryDto>> response = categoryRestController.getAllCategories();

        //THEN
        Mockito.verify(categoryRestService).getAllCategories();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateCategoryFound() {
        //GIVEN
        CategoryDto categoryDto = TestUtils.buildCategoryDto();
        categoryDto.setId(1L);

        Optional<CategoryDto> optCategoryDto = Optional.of(categoryDto);

        //WHEN
        Mockito.when(categoryRestService.updateCategory(categoryDto)).thenReturn(optCategoryDto);
        ResponseEntity<CategoryDto> response = categoryRestController.updateCategory(categoryDto);

        //THEN
        Mockito.verify(categoryRestService).updateCategory(categoryDto);
        assertEquals(optCategoryDto.get(), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateCategoryNotFound() {
        //GIVEN
        CategoryDto categoryDto = TestUtils.buildCategoryDto();
        categoryDto.setId(2L);

        //WHEN
        Mockito.when(categoryRestService.updateCategory(categoryDto)).thenReturn(Optional.empty());
        ResponseEntity<CategoryDto> response = categoryRestController.updateCategory(categoryDto);

        //THEN
        Mockito.verify(categoryRestService).updateCategory(categoryDto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


}
