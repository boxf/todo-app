package ch.cern.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Entity representing the task in the app
 */
@Data
@AllArgsConstructor
public class Task {

    private Long id;

    private String name;

    private String description;

    private Timestamp deadLine;

    private Category category;
}
