package ch.cern.todo.repository;

import ch.cern.todo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    Category save(Category category);

    @Override
    Category getById(Long categoryId);

}