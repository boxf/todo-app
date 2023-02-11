package ch.cern.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Entity representing category's task in the app
 */
@Data
@AllArgsConstructor
public class Category {

    private Long id;

    private String name;

    private String description;
}
