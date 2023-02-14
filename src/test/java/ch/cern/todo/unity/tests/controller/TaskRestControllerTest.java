package ch.cern.todo.unity.tests.controller;

import ch.cern.todo.controller.TaskRestController;
import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.service.rest.TaskRestService;
import ch.cern.todo.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskRestControllerTest {

    @InjectMocks
    private TaskRestController taskRestController;

    @Mock
    private TaskRestService taskRestService;

    @Test
    public void testPostTask() {
        // GIVEN
        TaskDto taskDto = TestUtils.buildTaskDto();

        //WHEN
        Mockito.when(taskRestService.addNewTask(taskDto)).thenReturn(null);
        ResponseEntity<Void> response = taskRestController.postTask(taskDto);

        //THEN
        Mockito.verify(taskRestService).addNewTask(taskDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetTaskFound() {
        // GIVEN
        Long id = 1L;

        Optional<TaskDto> optTaskDto = Optional.of(TestUtils.buildTaskDto());

        //WHEN
        Mockito.when(taskRestService.getTaskById(id)).thenReturn(optTaskDto);
        ResponseEntity<TaskDto> response = taskRestController.getTask(id);

        //THEN
        Mockito.verify(taskRestService).getTaskById(id);
        assertEquals(optTaskDto.get(), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetTaskNotFound() {
        // GIVEN
        Long id = 1L;

        //WHEN
        Mockito.when(taskRestService.getTaskById(id)).thenReturn(Optional.empty());
        ResponseEntity<TaskDto> response = taskRestController.getTask(id);

        //THEN
        Mockito.verify(taskRestService).getTaskById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
