package ch.cern.todo.service.repository;

import ch.cern.todo.model.Category;
import ch.cern.todo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Elementary service to manage Categorys and link them with the Repository
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(Long id) {
        return Optional.of(categoryRepository.getById(id));
    }

}
