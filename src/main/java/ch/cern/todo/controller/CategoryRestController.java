package ch.cern.todo.controller;

import ch.cern.todo.api.CategoryRestApi;
import ch.cern.todo.dto.CategoryDto;
import ch.cern.todo.model.Category;
import ch.cern.todo.service.rest.CategoryRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller of the {@link Category} REST WS
 */
@RestController
@RequestMapping(path = "/api/categories")
public class CategoryRestController implements CategoryRestApi {

    @Autowired
    private CategoryRestService categoryRestService;

    @Override
    @PostMapping(path = "/add")
    public ResponseEntity<Void> postCategory(CategoryDto categoryDto) {
        categoryRestService.addNewCategory(categoryDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) {
        Optional<CategoryDto> categoryDto = categoryRestService.getCategoryById(id);
        return categoryDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @PutMapping(path = "/update")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody final CategoryDto categoryDto) {
        Optional<CategoryDto> newCategoryDto = categoryRestService.updateCategory(categoryDto);
        return newCategoryDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
