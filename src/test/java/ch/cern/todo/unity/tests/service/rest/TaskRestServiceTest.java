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
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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
        when(taskMapper.mapTaskDtoToTask(taskDto)).thenReturn(task);
        when(taskService.addTask(task)).thenReturn(returnedTask);
        when(taskMapper.mapTaskToTaskDto(returnedTask)).thenReturn(returnedTaskDto);

        TaskDto taskDtoResult = taskRestService.addNewTask(taskDto);

        //THEN
        verify(taskMapper).mapTaskDtoToTask(taskDto);
        verify(taskService).addTask(task);
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
        when(taskService.getTaskById(id)).thenReturn(of(returnedTask));
        when(taskMapper.mapTaskToTaskDto(returnedTask)).thenReturn(returnedTaskDto);

        TaskDto TaskDtoResult = taskRestService.getTaskById(id).get();

        //THEN
        verify(taskMapper).mapTaskToTaskDto(returnedTask);
        verify(taskService).getTaskById(id);
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
        when(taskService.getTaskById(id)).thenReturn(empty());

        Optional<TaskDto> TaskDtoResult = taskRestService.getTaskById(id);

        //THEN
        verify(taskMapper, never()).mapTaskToTaskDto(returnedTask);
        verify(taskService).getTaskById(id);
        assertEquals(empty(), TaskDtoResult);
    }

    @Test
    public void testGetAllTaskReturnValue() {
        // GIVEN
        Collection<Task> returnedTasks = TestUtils.buildCollectionTask();

        TaskDto returnedTaskDto = TestUtils.buildTaskDto();
        returnedTaskDto.setId(1L);

        //WHEN
        when(taskService.getAllTasks()).thenReturn((returnedTasks));
        when(taskMapper.mapTaskToTaskDto(any())).thenReturn(returnedTaskDto);

        Collection<TaskDto> TasksDtoResult = taskRestService.getAllTasks();

        //THEN
        verify(taskMapper, times(3)).mapTaskToTaskDto(any());
        verify(taskService).getAllTasks();
        assertEquals(3, TasksDtoResult.size());
    }

    @Test
    public void testGetAllTaskReturnEmpty() {
        // GIVEN
        Task returnedTask = TestUtils.buildTask();
        returnedTask.setId(1L);

        TaskDto returnedTaskDto = TestUtils.buildTaskDto();
        returnedTaskDto.setId(1L);

        //WHEN
        when(taskService.getAllTasks()).thenReturn(Collections.emptyList());

        Collection<TaskDto> TaskDtoResult = taskRestService.getAllTasks();

        //THEN
        verify(taskMapper, never()).mapTaskToTaskDto(returnedTask);
        verify(taskService).getAllTasks();
        assertEquals(Collections.EMPTY_LIST, TaskDtoResult);
    }

    @Test
    public void testUpdateTaskReturnValue() {
        // GIVEN
        TaskDto taskDto = TestUtils.buildTaskDto();
        taskDto.setId(1L);

        Task mappedTask = TestUtils.buildTask();
        mappedTask.setId(1L);

        Task returnedTask = TestUtils.buildTask();
        returnedTask.setId(1L);

        TaskDto returnedTaskDto = TestUtils.buildTaskDto();
        returnedTaskDto.setId(1L);

        //WHEN
        when(taskMapper.mapTaskDtoToTask(taskDto)).thenReturn(mappedTask);
        when(taskService.updateTask(mappedTask)).thenReturn(of(returnedTask));
        when(taskMapper.mapTaskToTaskDto(returnedTask)).thenReturn(returnedTaskDto);

        TaskDto TaskDtoResult = taskRestService.updateTask(taskDto).get();

        //THEN
        verify(taskMapper).mapTaskDtoToTask(taskDto);
        verify(taskMapper).mapTaskToTaskDto(returnedTask);
        verify(taskService).updateTask(mappedTask);
        assertEquals(returnedTask.getId(), TaskDtoResult.getId());
        assertEquals(returnedTask.getName(), TaskDtoResult.getName());
        assertEquals(returnedTask.getDescription(), TaskDtoResult.getDescription());
        assertEquals(returnedTask.getDeadLine(), TaskDtoResult.getDeadLine());
    }

    @Test
    public void testUpdateTaskReturnEmpty() {
        // GIVEN
        TaskDto taskDto = TestUtils.buildTaskDto();
        taskDto.setId(1L);

        Task mappedTask = TestUtils.buildTask();
        mappedTask.setId(1L);

        Task returnedTask = TestUtils.buildTask();
        returnedTask.setId(1L);

        TaskDto returnedTaskDto = TestUtils.buildTaskDto();
        returnedTaskDto.setId(1L);

        //WHEN
        when(taskMapper.mapTaskDtoToTask(taskDto)).thenReturn(mappedTask);
        when(taskService.updateTask(mappedTask)).thenReturn(empty());

        Optional<TaskDto> TaskDtoResult = taskRestService.updateTask(taskDto);

        //THEN
        verify(taskMapper).mapTaskDtoToTask(taskDto);
        verify(taskService).updateTask(mappedTask);
        verify(taskMapper, never()).mapTaskToTaskDto(returnedTask);
        assertEquals(empty(), TaskDtoResult);
    }

    @Test
    public void testDeleteTask() {
        //GIVEN
        Long id = 1L;

        //WHEN
        when(taskService.deleteTask(id)).thenReturn(true);
        boolean response = taskRestService.deleteTask(id);

        //THEN
        verify(taskService).deleteTask(id);
        assertTrue(response);

    }

}
