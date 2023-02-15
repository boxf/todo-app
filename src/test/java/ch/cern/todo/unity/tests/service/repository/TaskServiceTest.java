package ch.cern.todo.unity.tests.service.repository;

import ch.cern.todo.model.Task;
import ch.cern.todo.repository.TaskRepository;
import ch.cern.todo.service.repository.TaskService;
import ch.cern.todo.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testAddTask() {
        // GIVEN
        Task task = TestUtils.buildTask();

        Task returnedTask = TestUtils.buildTask();
        returnedTask.setId(1L);

        //WHEN
        when(taskRepository.save(task)).thenReturn(returnedTask);

        Task taskResult = taskService.addTask(task);

        //THEN
        verify(taskRepository).save(task);
        assertEquals(task.getName(), taskResult.getName());
        assertEquals(task.getDescription(), taskResult.getDescription());
        assertEquals(task.getDeadLine(), taskResult.getDeadLine());
        assertTrue(taskResult.getId() == 1L);
    }

    @Test
    public void testGetTaskByIdReturnValue() {
        // GIVEN
        Long id = 1L;

        Task returnedTask = TestUtils.buildTask();
        returnedTask.setId(1L);

        //WHEN
        when(taskRepository.findById(id)).thenReturn(Optional.of(returnedTask));

        Task taskResult = taskService.getTaskById(id).get();

        //THEN
        verify(taskRepository).findById(id);
        assertEquals(returnedTask.getId(), taskResult.getId());
        assertEquals(returnedTask.getName(), taskResult.getName());
        assertEquals(returnedTask.getDescription(), taskResult.getDescription());
        assertEquals(returnedTask.getDeadLine(), taskResult.getDeadLine());
    }

    @Test
    public void testGetTaskByIdReturnEmpty() {
        // GIVEN
        Long id = 1L;

        Task returnedTask = TestUtils.buildTask();
        returnedTask.setId(1L);

        //WHEN
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Task> TaskResult = taskService.getTaskById(id);

        //THEN
        verify(taskRepository).findById(id);
        assertEquals(Optional.empty(), TaskResult);
    }

    @Test
    public void testGetAllTasksReturnValue() {
        // GIVEN
        Collection<Task> returnedTasks = TestUtils.buildCollectionTask();

        //WHEN
        when(taskRepository.findAll()).thenReturn((List<Task>) returnedTasks);

        Collection<Task> taskResult = taskService.getAllTasks();

        //THEN
        verify(taskRepository).findAll();
        assertEquals(3, taskResult.size());
    }

    @Test
    public void testGetAllTasksReturnEmpty() {
        //WHEN
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());

        Collection<Task> TaskResult = taskService.getAllTasks();

        //THEN
        verify(taskRepository).findAll();
        assertEquals(Collections.EMPTY_LIST, TaskResult);
    }

    @Test
    public void testUpdateTask() {
        // GIVEN
        Task task = TestUtils.buildTask();
        task.setId(1L);

        Task returnedTask = TestUtils.buildTask();
        returnedTask.setId(1L);

        //WHEN
        when(taskRepository.findById(1L)).thenReturn(Optional.of(returnedTask));
        when(taskRepository.save(any(Task.class))).thenReturn(returnedTask);

        Task taskResult = taskService.updateTask(task).get();

        //THEN
        verify(taskRepository).save(any(Task.class));
        assertEquals(returnedTask.getId(), taskResult.getId());
        assertEquals(returnedTask.getName(), taskResult.getName());
        assertEquals(returnedTask.getDescription(), taskResult.getDescription());
        assertEquals(returnedTask.getDeadLine(), taskResult.getDeadLine());
        assertEquals(returnedTask.getCategory(), taskResult.getCategory());
    }

    @Test
    public void testUpdateTaskEmpty() {
        // GIVEN
        Task task = TestUtils.buildTask();
        task.setId(1L);

        Task returnedTask = TestUtils.buildTask();
        returnedTask.setId(1L);

        //WHEN
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Task> optionalTaskResult = taskService.updateTask(task);

        //THEN
        verify(taskRepository).findById(1L);
        assertEquals(Optional.empty(), optionalTaskResult);
    }

    @Test
    public void testDeleteTask() {
        //GIVEN
        Long id = 1L;
        Task task = TestUtils.buildTask();

        //WHEN
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        boolean response = taskService.deleteTask(id);

        //THEN
        verify(taskRepository).findById(id);
        verify(taskRepository).deleteById(id);
        assertTrue(response);
    }

    @Test
    public void testDeleteTaskIdNotFound() {
        //GIVEN
        Long id = 1L;

        //WHEN
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        boolean response = taskService.deleteTask(id);

        //THEN
        verify(taskRepository).findById(id);
        verify(taskRepository, never()).deleteById(id);
        assertFalse(response);
    }
}
