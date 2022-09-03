package task;

import java.time.LocalDateTime;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrintTaskDto extends TaskDto {
    private int id;

    private LocalDateTime createDate;

    public PrintTaskDto(int id, String description, LocalDateTime createDate, String priority) {
        super(description, priority);
        this.id = id;
        this.createDate = createDate;
    }


    @Override
    public String toString() {
        return "Zadanie - {" + "id=" + id + ", opis=" + getDescription() +
               ", data utworzenia zadania= " + createDate.toString() + ", priorytet=" + getPriority() + '}';
    }
}
