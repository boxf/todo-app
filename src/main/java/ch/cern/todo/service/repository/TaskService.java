package ch.cern.todo.service.repository;

import ch.cern.todo.model.Task;
import ch.cern.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Elementary service to manage Tasks and link them with the Repository
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Save a new {@link Task} in the DB
     *
     * @param task
     * @return
     */
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Method get the entity by id. If the entity is not found it throws an {@link EntityNotFoundException} but also an
     * Entity that is going to be empty. We don't want an empty Entity
     *
     * @param id
     * @return Optional<Task> task
     */
    public Optional<Task> getTaskById(Long id) {
        try {
            Task foundTask = taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            return Optional.of(foundTask);
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    /**
     * Update an existing {@link Task} with the new values received
     *
     * @param task
     * @return
     */
    public Optional<Task> updateTask(Task task) {
        Optional<Task> existingOptionalTask = getTaskById(task.getId());

        if (existingOptionalTask.isPresent()) {
            Task existingTask = existingOptionalTask.get();
            existingTask.setName(task.getName());
            existingTask.setDescription(task.getDescription());
            existingTask.setDeadLine(task.getDeadLine());
            existingTask.setCategory(task.getCategory());

            return Optional.of(taskRepository.save(existingTask));

        } else {
            return Optional.empty();
        }

    }

}
