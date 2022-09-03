package task;

import lombok.*;

//@Data to to samo co: @Getter, @Setter, @ToString, @EqualsAndHashCode, @NoArgsConstructor
//DTO - data transfer object
@Data
@AllArgsConstructor
public class TaskDto {
    private String description;
    private String priority;

    public TaskDto(String description) {
        this.description = description;
    }
}