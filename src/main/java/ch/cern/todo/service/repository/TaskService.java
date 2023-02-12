package ch.cern.todo.service.repository;

import ch.cern.todo.model.Task;
import ch.cern.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Elementary service to manage Tasks and link them with the Repository
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return Optional.of(taskRepository.getById(id));
    }

}
