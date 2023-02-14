package ch.cern.todo.mapper;

import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO : verifier le type du bean à mettre
 */
@Service
public class TaskMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * Maps a TaskDtoIn to a Task
     *
     * @param taskDto
     * @return task
     */
    public Task mapTaskDtoInToTask(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setDeadLine(taskDto.getDeadLine());
        task.setCategory(categoryMapper.mapCategoryDtoToCategory(taskDto.getCategoryDto()));

        return task;
    }

    /**
     * Maps a Task to a TaskDto
     *
     * @param task
     * @return taskDto
     */
    public TaskDto mapTaskToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setDeadLine(task.getDeadLine());
        taskDto.setCategoryDto(categoryMapper.mapCategoryToCategoryDto(task.getCategory()));

        return taskDto;
    }
}
