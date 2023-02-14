package ch.cern.todo.api;

import ch.cern.todo.dto.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Interface of the Task API
 */
public interface TaskRestApi {

    /**
     * Controller to save a new {@link ch.cern.todo.model.Task} to the DB
     *
     * @param taskDto
     * @return
     */
    ResponseEntity<Void> postTask(@RequestBody TaskDto taskDto);

    /**
     * Retrieve a {@link ch.cern.todo.model.Task} from the DB with its id
     * @param id
     * @return
     */
    ResponseEntity<TaskDto> getTask(@RequestParam Long id);

    /**
     * Update a given {@link ch.cern.todo.model.Task} with the new sent values
     * @param taskDto
     * @return
     */
    ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto);
}
