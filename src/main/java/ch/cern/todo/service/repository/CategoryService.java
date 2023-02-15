package ch.cern.todo.service.repository;

import ch.cern.todo.model.Category;
import ch.cern.todo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Elementary service to manage {@link Category} and link them with the Repository
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Elementary service for adding the given {@link Category} to the DB
     * @param category
     * @return
     */
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Method get the entity by id. If the entity is not found it throws an {@link EntityNotFoundException} but also an
     * Entity that is going to be empty. We don't want an empty Entity
     * @param id
     * @return
     */
    public Optional<Category> getCategoryById(Long id) {
        try {
            Category foundCategory = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            return Optional.of(foundCategory);
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    /**
     * Update an existing {@link Category} with the new values received
     *
     * @param category
     * @return
     */
    public Optional<Category> updateCategory(Category category) {
        Optional<Category> existingOptionalCategory = getCategoryById(category.getId());

        if (existingOptionalCategory.isPresent()) {
            Category existingCategory = existingOptionalCategory.get();
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());

            return Optional.of(categoryRepository.save(existingCategory));

        } else {
            return Optional.empty();
        }

    }

}
