package ch.cern.todo.api;

import ch.cern.todo.dto.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

/**
 * Interface of the Task API
 */
public interface TaskRestApi {

    ResponseEntity<Void> postTask(@RequestBody TaskDto taskDto);

    ResponseEntity<TaskDto> getTask(Long id);
}
