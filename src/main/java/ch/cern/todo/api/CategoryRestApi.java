package ch.cern.todo.api;

import ch.cern.todo.dto.CategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface of the Categories API
 */
public interface CategoryRestApi {
    
    ResponseEntity<Void> postCategory(@RequestBody CategoryDto categoryDto);

    ResponseEntity<CategoryDto> getCategory(Long id);
}
