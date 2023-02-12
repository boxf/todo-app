package ch.cern.todo.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Entity representing the task in the app
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
    private String name;

    @Column(name = "TASK_DESCRIPTION")
    private String description;

    @Column(name = "DEADLINE")
    private Timestamp deadLine;

    @JoinColumn(name = "CATEGORY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
