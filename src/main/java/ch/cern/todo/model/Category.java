package ch.cern.todo.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


/**
 * TodoEntity representing category's task in the app
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TASK_CATEGORY")
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CATEGORY_NAME")
    @Size(max = 100)
    private String name;

    @Column(name = "CATEGORY_DESCRIPTION")
    @Size(max = 500)
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Task> tasks = new HashSet<>();
}
