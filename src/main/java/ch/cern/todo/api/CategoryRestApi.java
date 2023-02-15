package ch.cern.todo.api;

import ch.cern.todo.model.Category;
import ch.cern.todo.dto.CategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Interface of the Categories API
 */
public interface CategoryRestApi {

    /**
     * Post method to insert a new {@link Category} in the DB
     * @param categoryDto
     * @return
     */
    ResponseEntity<Void> postCategory(@RequestBody CategoryDto categoryDto);

    /**
     * Get method to retrieve the {@link Category} with the given id
     * @param id
     * @return
     */
    ResponseEntity<CategoryDto> getCategory(@RequestParam Long id);

    /**
     * Update a given {@link Category} with the new sent values
     * @param categoryDto
     * @return
     */
    ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto);
}
