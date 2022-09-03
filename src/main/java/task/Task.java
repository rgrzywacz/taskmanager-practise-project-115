package task;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @Column(name = "create_task_date")
    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, LocalDateTime createDate, Priority priority) {
        this.description = description;
        this.createDate = createDate;
        this.priority = priority;
    }


}
