package ch.cern.todo.unity.tests.mapper;

import ch.cern.todo.dto.CategoryDto;
import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.mapper.CategoryMapper;
import ch.cern.todo.mapper.TaskMapper;
import ch.cern.todo.model.Category;
import ch.cern.todo.model.Task;
import ch.cern.todo.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @Mock
    private CategoryMapper categoryMapper;

    @Test
    public void testMapTaskDtoInToTask() {
        // GIVEN
        TaskDto taskDto = TestUtils.buildTaskDto();
        CategoryDto categoryDto = TestUtils.buildCategoryDto();
        taskDto.setCategoryDto(categoryDto);
        Category category = TestUtils.buildCategory();

        //WHEN
        Mockito.when(categoryMapper.mapCategoryDtoToCategory(taskDto.getCategoryDto())).thenReturn(category);
        Task mappedTask = taskMapper.mapTaskDtoToTask(taskDto);

        //THEN
        Mockito.verify(categoryMapper).mapCategoryDtoToCategory(taskDto.getCategoryDto());
        assertEquals(taskDto.getName(), mappedTask.getName());
        assertEquals(taskDto.getDescription(), mappedTask.getDescription());
        assertEquals(taskDto.getDeadLine(), mappedTask.getDeadLine());
        assertEquals(taskDto.getCategoryDto().getName(), mappedTask.getCategory().getName());
    }

    @Test
    public void testMapTaskDtoNullToTask() {
        //WHEN
        Task mappedTask = taskMapper.mapTaskDtoToTask(null);

        //THEN
        assertNull(mappedTask);
    }

    @Test
    public void testMapTaskToTaskDto() {
        // GIVEN
        Task task = TestUtils.buildTask();
        Category category = TestUtils.buildCategory();
        task.setCategory(category);
        CategoryDto categoryDto = TestUtils.buildCategoryDto();

        //WHEN
        Mockito.when(categoryMapper.mapCategoryToCategoryDto(task.getCategory())).thenReturn(categoryDto);
        TaskDto mappedTaskDto = taskMapper.mapTaskToTaskDto(task);

        //THEN
        Mockito.verify(categoryMapper).mapCategoryToCategoryDto(task.getCategory());
        assertEquals(task.getName(), mappedTaskDto.getName());
        assertEquals(task.getDescription(), mappedTaskDto.getDescription());
        assertEquals(task.getDeadLine(), mappedTaskDto.getDeadLine());
        assertEquals(task.getCategory().getName(), mappedTaskDto.getCategoryDto().getName());
    }

    @Test
    public void testMapTaskNullToTaskDto() {
        //WHEN
        TaskDto mappedTaskDto = taskMapper.mapTaskToTaskDto(null);

        //THEN
        assertNull(mappedTaskDto);
    }

}
