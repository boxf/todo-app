package ch.cern.todo.unity.tests.service.repository;

import ch.cern.todo.model.Task;
import ch.cern.todo.repository.TaskRepository;
import ch.cern.todo.service.repository.TaskService;
import ch.cern.todo.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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
        Mockito.when(taskRepository.save(task)).thenReturn(returnedTask);

        Task taskResult = taskService.addTask(task);

        //THEN
        Mockito.verify(taskRepository).save(task);
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
        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(returnedTask));

        Task taskResult = taskService.getTaskById(id).get();

        //THEN
        Mockito.verify(taskRepository).findById(id);
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
        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Task> TaskResult = taskService.getTaskById(id);

        //THEN
        Mockito.verify(taskRepository).findById(id);
        assertEquals(Optional.empty(), TaskResult);
    }


}
