package ch.cern.todo.unity.tests.service.rest;

import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.mapper.TaskMapper;
import ch.cern.todo.model.Task;
import ch.cern.todo.service.repository.TaskService;
import ch.cern.todo.service.rest.TaskRestService;
import ch.cern.todo.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskRestServiceTest {

    @InjectMocks
    private TaskRestService taskRestService;

    @Mock
    private TaskService taskService;

    @Mock
    private TaskMapper taskMapper;

    @Test
    public void testAddNewTask() {
        // GIVEN
        TaskDto taskDto = TestUtils.buildTaskDto();

        Task task = TestUtils.buildTask();

        Task returnedTask = TestUtils.buildTask();
        returnedTask.setId(1L);

        TaskDto returnedTaskDto = TestUtils.buildTaskDto();
        returnedTaskDto.setId(1L);

        //WHEN
        Mockito.when(taskMapper.mapTaskDtoToTask(taskDto)).thenReturn(task);
        Mockito.when(taskService.addTask(task)).thenReturn(returnedTask);
        Mockito.when(taskMapper.mapTaskToTaskDto(returnedTask)).thenReturn(returnedTaskDto);

        TaskDto taskDtoResult = taskRestService.addNewTask(taskDto);

        //THEN
        Mockito.verify(taskMapper).mapTaskDtoToTask(taskDto);
        Mockito.verify(taskService).addTask(task);
        assertEquals(task.getName(), taskDtoResult.getName());
        assertEquals(task.getDescription(), taskDtoResult.getDescription());
        assertEquals(task.getDeadLine(), taskDtoResult.getDeadLine());
    }

    @Test
    public void testGetTaskByIdReturnValue() {
        // GIVEN
        Long id = 1L;

        Task returnedTask = TestUtils.buildTask();
        returnedTask.setId(1L);

        TaskDto returnedTaskDto = TestUtils.buildTaskDto();
        returnedTaskDto.setId(1L);

        //WHEN
        Mockito.when(taskService.getTaskById(id)).thenReturn(Optional.of(returnedTask));
        Mockito.when(taskMapper.mapTaskToTaskDto(returnedTask)).thenReturn(returnedTaskDto);

        TaskDto TaskDtoResult = taskRestService.getTaskById(id).get();

        //THEN
        Mockito.verify(taskMapper).mapTaskToTaskDto(returnedTask);
        Mockito.verify(taskService, Mockito.times(2)).getTaskById(id);
        assertEquals(returnedTask.getId(), TaskDtoResult.getId());
        assertEquals(returnedTask.getName(), TaskDtoResult.getName());
        assertEquals(returnedTask.getDescription(), TaskDtoResult.getDescription());
        assertEquals(returnedTask.getDeadLine(), TaskDtoResult.getDeadLine());
    }

    @Test
    public void testGetTaskByIdReturnEmpty() {
        // GIVEN
        Long id = 1L;

        Task returnedTask = TestUtils.buildTask();
        returnedTask.setId(1L);

        TaskDto returnedTaskDto = TestUtils.buildTaskDto();
        returnedTaskDto.setId(1L);

        //WHEN
        Mockito.when(taskService.getTaskById(id)).thenReturn(Optional.empty());

        Optional<TaskDto> TaskDtoResult = taskRestService.getTaskById(id);

        //THEN
        Mockito.verify(taskMapper, Mockito.never()).mapTaskToTaskDto(returnedTask);
        Mockito.verify(taskService).getTaskById(id);
        assertEquals(Optional.empty(), TaskDtoResult);
    }


}
