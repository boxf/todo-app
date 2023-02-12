package ch.cern.todo.controller;

import ch.cern.todo.api.TaskRestApi;
import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.service.repository.TaskService;
import ch.cern.todo.service.rest.TaskRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller of the Task REST WS
 */
@RestController
@RequestMapping(path = "/api/tasks")
public class TaskRestController implements TaskRestApi {

    @Autowired
    private TaskRestService taskRestService;

    @Override
    @PostMapping(path = "/add")
    public ResponseEntity<Void> postTask(TaskDto taskDto) {
        taskRestService.addNewTask(taskDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping("/get")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        Optional<TaskDto> taskDto = taskRestService.getTaskById(id);
        if (taskDto.isPresent()){
            return ResponseEntity.ok(taskDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
