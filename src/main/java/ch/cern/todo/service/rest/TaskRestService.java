package ch.cern.todo.service.rest;

import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.mapper.TaskMapper;
import ch.cern.todo.model.Task;
import ch.cern.todo.service.repository.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Orchestration service used to manage Tasks for the TODOapp
 */
@Service
public class TaskRestService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    /**
     * Adds a new task to the DB
     *
     * @param taskDto
     * @return
     */
    public TaskDto addNewTask(TaskDto taskDto) {
        Task task = taskMapper.mapTaskDtoInToTask(taskDto);
        return taskMapper.mapTaskToTaskDto(taskService.addTask(task));
    }

    /**
     * Retrieve a task in DB with the given id
     *
     * @param id
     * @return
     */
    // TODO : verify the architecture. It does not seem to be really well implemented
    public Optional<TaskDto> getTaskById(Long id) {
        TaskDto taskDto = new TaskDto();

        if (taskService.getTaskById(id).isPresent()) {
            return Optional.of(taskMapper.mapTaskToTaskDto(taskService.getTaskById(id).get()));

        } else {
            return Optional.empty();
        }

    }


}
