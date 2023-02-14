package ch.cern.todo.controller;

import ch.cern.todo.api.TaskRestApi;
import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.service.rest.TaskRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Controller of the Task REST WS
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
        if (taskDto.isPresent()){
            return ResponseEntity.ok(taskDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<TaskDto> updateTask(@RequestBody final TaskDto taskDto) {

        
        return null;
    }

}
