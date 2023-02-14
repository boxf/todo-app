package ch.cern.todo.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * TodoEntity representing the task in the app
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private Long id;

    @Column(name = "TASK_NAME")
    @Size(max = 100)
    private String name;

    @Column(name = "TASK_DESCRIPTION")
    @Size(max = 500)
    private String description;

    @Column(name = "DEADLINE")
    private Timestamp deadLine;

    @JoinColumn(name = "CATEGORY_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
