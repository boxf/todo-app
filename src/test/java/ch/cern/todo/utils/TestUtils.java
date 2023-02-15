package ch.cern.todo.utils;

import ch.cern.todo.dto.CategoryDto;
import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.model.Category;
import ch.cern.todo.model.Task;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;

@UtilityClass
public class TestUtils {


    public static TaskDto buildTaskDto() {
        TaskDto taskDto = new TaskDto();
        taskDto.setName("CERN TODO Project");
        taskDto.setDescription("Finish the project sent by Lukasz");
        taskDto.setDeadLine(Timestamp.valueOf("2023-02-15 23:59:59.999"));

        return taskDto;
    }

    public static Task buildTask() {
        Task task = new Task();
        task.setName("CERN TODO Project");
        task.setDescription("Finish the project sent by Lukasz");
        task.setDeadLine(Timestamp.valueOf("2023-02-15 23:59:59.999"));

        return task;
    }

    public static CategoryDto buildCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("CERN TODO Project");
        categoryDto.setDescription("Finish the project sent by Lukasz");

        return categoryDto;
    }

    public static Category buildCategory() {
        Category category = new Category();
        category.setName("CERN TODO Project");
        category.setDescription("Finish the project sent by Lukasz");

        return category;
    }


}
