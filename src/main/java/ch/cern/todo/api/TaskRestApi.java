package ch.cern.todo.api;

import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * Interface of the Task API
 */
public interface TaskRestApi {

    /**
     * Controller to save a new {@link Task} to the DB
     *
     * @param taskDto
     * @return
     */
    ResponseEntity<Void> postTask(@RequestBody TaskDto taskDto);

    /**
     * Retrieve a {@link Task} from the DB with its id
     * @param id
     * @return
     */
    ResponseEntity<TaskDto> getTask(@RequestParam Long id);

    /**
     * Retrieve all {@link Task} from the DB
     * @return
     */
    ResponseEntity<Collection<TaskDto>> getAllTasks();

    /**
     * Update a given {@link Task} with the new sent values
     * @param taskDto
     * @return
     */
    ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto);

    /**
     * Delete a {@link Task} with the given id
     * @param id
     * @return
     */
    ResponseEntity deleteTask(@RequestParam Long id);
}
