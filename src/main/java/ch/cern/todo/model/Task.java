package ch.cern.todo.model;

import java.sql.Timestamp;

public class Task {

    private Long id;

    private String name;

    private String description;

    private Timestamp deadLine;

    private Category category;
}
