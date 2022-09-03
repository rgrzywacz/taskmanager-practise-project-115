package task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskService {
    private TaskDao taskDao;

    public void addTask(TaskDto taskDto) throws IllegalArgumentException {
        if (taskDto.getDescription() != null && taskDto.getDescription().length() > 3) {
            Priority priority = Priority.valueOf(taskDto.getPriority());
            Task task = new Task(taskDto.getDescription(), LocalDateTime.now(), priority);
            taskDao.insert(task);
        } else {
            throw new IllegalArgumentException("Opis zadania nie został podany");
        }
    }

    public List<PrintTaskDto> getAllTasks() {
        List<Task> tasks = taskDao.findAll();
        return tasks.stream().map(t -> new PrintTaskDto(t.getId(), t.getDescription(), t.getCreateDate(), t.getPriority().name()))
             .collect(Collectors.toList());
    }

    public void deleteTask(int taskIdToBeDeleted) {
        if (taskIdToBeDeleted > 0) {
            taskDao.deleteById(taskIdToBeDeleted);
        } else {
            throw new IllegalArgumentException("Id zadania jest mniejsze bądź równe 0");
        }
    }

    public void updateTask(TaskDto taskDto, int taskIdToBeUpdated) {
        if (taskDto.getDescription() != null && taskDto.getDescription().length() > 3
            && taskIdToBeUpdated > 0) {
            Task task = new Task(taskIdToBeUpdated, taskDto.getDescription());
            taskDao.update(task);
        } else {
            throw new IllegalArgumentException("Opis zadania nie został podany lub id zadania jest mniejsze bądź równe 0");
        }
    }
}
