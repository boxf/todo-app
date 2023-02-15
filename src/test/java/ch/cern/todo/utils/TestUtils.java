package ch.cern.todo.utils;

import ch.cern.todo.dto.CategoryDto;
import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.model.Category;
import ch.cern.todo.model.Task;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

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

    public static Collection<TaskDto> buildCollectionTaskDto() {
        TaskDto taskDto1 = buildTaskDto();
        taskDto1.setId(1L);
        TaskDto taskDto2 = buildTaskDto();
        taskDto2.setId(2L);
        TaskDto taskDto3 = buildTaskDto();
        taskDto3.setId(3L);

        Collection<TaskDto> tasksDto = new ArrayList<>();
        tasksDto.add(taskDto1);
        tasksDto.add(taskDto2);
        tasksDto.add(taskDto3);

        return tasksDto;
    }

    public static Collection<Task> buildCollectionTask() {
        Task task1 = buildTask();
        task1.setId(1L);
        Task task2 = buildTask();
        task2.setId(2L);
        Task task3 = buildTask();
        task3.setId(3L);

        Collection<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        return tasks;
    }

    public static Collection<CategoryDto> buildCollectionCategoryDto() {
        CategoryDto categoryDto1 = buildCategoryDto();
        categoryDto1.setId(1L);
        CategoryDto categoryDto2 = buildCategoryDto();
        categoryDto2.setId(2L);
        CategoryDto categoryDto3 = buildCategoryDto();
        categoryDto3.setId(3L);

        Collection<CategoryDto> categoriesDto = new ArrayList<>();
        categoriesDto.add(categoryDto1);
        categoriesDto.add(categoryDto2);
        categoriesDto.add(categoryDto3);

        return categoriesDto;
    }

    public static Collection<Category> buildCollectionCategory() {
        Category category1 = buildCategory();
        category1.setId(1L);
        Category category2 = buildCategory();
        category2.setId(2L);
        Category category3 = buildCategory();
        category3.setId(3L);

        Collection<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        return categories;
    }


}
