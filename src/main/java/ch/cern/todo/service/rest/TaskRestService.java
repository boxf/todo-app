package ch.cern.todo.service.rest;

import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.mapper.TaskMapper;
import ch.cern.todo.model.Task;
import ch.cern.todo.service.repository.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import java.util.Optional;
import java.util.stream.Collectors;

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
     * @return TaskDto
     */
    public TaskDto addNewTask(TaskDto taskDto) {
        Task task = taskMapper.mapTaskDtoToTask(taskDto);
        return taskMapper.mapTaskToTaskDto(taskService.addTask(task));
    }

    /**
     * Retrieve a task in DB with the given id and convert it to a {@link Optional<TaskDto>}
     *
     * @param id
     * @return Optional<TaskDto>
     */
    public Optional<TaskDto> getTaskById(Long id) {
        Optional<Task> optionalTask = taskService.getTaskById(id);

        return optionalTask.map(task -> taskMapper.mapTaskToTaskDto(task));
    }

    /**
     * Retrieve all task in DB and convert it to a {@link Collection<TaskDto>}
     *
     * @return Optional<TaskDto>
     */
    public Collection<TaskDto> getAllTasks() {
        Collection<Task> tasks = taskService.getAllTasks();

        return tasks.stream().map((task) -> taskMapper.mapTaskToTaskDto(task)).collect(Collectors.toList());
    }

    /**
     * Update the {@link Task} corresponding to the given {@link TaskDto} with the new values
     *
     * @param taskDto
     * @return
     */
    public Optional<TaskDto> updateTask(TaskDto taskDto) {
        Task mappedTask = taskMapper.mapTaskDtoToTask(taskDto);

        Optional<Task> updatedTask = taskService.updateTask(mappedTask);

        return updatedTask.map(task -> taskMapper.mapTaskToTaskDto(task));
    }

    /**
     * Delete a {@link Task} in DB by given id
     * @param id
     * @return
     */
    public boolean deleteTask(Long id){
        return taskService.deleteTask(id);
    }


}
