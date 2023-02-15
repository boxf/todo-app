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

}