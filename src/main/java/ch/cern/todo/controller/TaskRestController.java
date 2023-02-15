package ch.cern.todo.controller;

import ch.cern.todo.api.TaskRestApi;
import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.model.Task;
import ch.cern.todo.service.rest.TaskRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

/**
 * Controller of the {@link Task} REST WS
 */
@RestController
@Transactional
@RequestMapping(path = "/api/tasks")
public class TaskRestController implements TaskRestApi {

    @Autowired
    private TaskRestService taskRestService;

    @Override
    @PostMapping(path = "/add")
    public ResponseEntity<Void> postTask(@RequestBody final TaskDto taskDto) {
        taskRestService.addNewTask(taskDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> getTask(@RequestParam final Long id) {
        Optional<TaskDto> taskDto = taskRestService.getTaskById(id);
        return taskDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<TaskDto>> getAllTasks() {
        Collection<TaskDto> tasksDto = taskRestService.getAllTasks();
        if (tasksDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tasksDto);
        }
    }

    @Override
    @PutMapping(path = "/update")
    public ResponseEntity<TaskDto> updateTask(@RequestBody final TaskDto taskDto) {
        Optional<TaskDto> newTaskDto = taskRestService.updateTask(taskDto);
        return newTaskDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping(path = "/delete")
    public ResponseEntity deleteTask(@RequestParam Long id) {
        if (taskRestService.deleteTask(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
