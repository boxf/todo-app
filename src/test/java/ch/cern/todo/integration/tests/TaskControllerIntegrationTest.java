package ch.cern.todo.integration.tests;

import ch.cern.todo.AppConfig;
import ch.cern.todo.controller.TaskRestController;
import ch.cern.todo.dto.CategoryDto;
import ch.cern.todo.dto.TaskDto;
import ch.cern.todo.utils.JsonTestUtils;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static ch.cern.todo.utils.TestUtils.buildCategoryDto;
import static ch.cern.todo.utils.TestUtils.buildTaskDto;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql({ "/drop_schema.sql", "/create_schema.sql", "/insert_data1.sql" })
@Sql({})
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class TaskControllerIntegrationTest {

    private static final String TASK_URI = "/api/tasks";

    private static final String LOCALHOST_8080 = "http://localhost:8080";

    @MockBean
    private TaskRestController taskRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    public void testPostTask() throws Exception {
        // GIVEN
        TaskDto taskDto = buildTaskDto();

        // WHEN
        taskRestController.postTask(taskDto);

        // THEN post a task
        mockMvc.perform(post(LOCALHOST_8080 + TASK_URI + "/add")
                        .content(JsonTestUtils.asJsonString(taskDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); //

        // get the posted task
        mockMvc.perform(get(LOCALHOST_8080 + TASK_URI + "/get")
                        .contentType(MediaType.APPLICATION_JSON).param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("Lorem ipsum")))
                .andExpect(jsonPath("$[1].name", is(taskDto.getName())))
                .andExpect(jsonPath("$.deadLine", is(taskDto.getDeadLine())));

    }

    @Test
    @Ignore
    public void testGetTask() throws Exception {
        // GIVEN
        Long id = 1L;

        TaskDto taskDto = buildTaskDto();
        taskDto.setId(id);

        CategoryDto categoryDto = buildCategoryDto();
        categoryDto.setId(1L);

        taskDto.setCategoryDto(categoryDto);

        // WHEN
        taskRestController.getTask(id);

        // THEN test get
        mockMvc.perform(get(LOCALHOST_8080 + TASK_URI + "/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].name").value(taskDto.getName()),
                        jsonPath("$[0].description").value(taskDto.getDescription()),
                        jsonPath("$[0].deadLine").value(taskDto.getDeadLine()),
                        jsonPath("$[0].category.id").value(taskDto.getCategoryDto().getId()));
    }



//    @Test
//    void ensureThatUserAPICallReturnStatusCode200() throws Exception {
//        HttpClient client = HttpClient.newBuilder().build();
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("localhost:8080/api/tasks/add")).build();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        assertTrue(response.statusCode()== 200);
//    }
}
