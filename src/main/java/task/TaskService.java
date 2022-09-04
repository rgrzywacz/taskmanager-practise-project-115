package task;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private static final List<String> AVAILABLE_PRIORITIES = Arrays.stream(Priority.values()).map(Enum::name).toList();
    private final TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public void addTask(TaskDto taskDto) throws IllegalArgumentException {
        if (taskDto.getDescription() != null && taskDto.getDescription().length() > 3 && AVAILABLE_PRIORITIES.contains(taskDto.getPriority())) {
            Priority priority = Priority.valueOf(taskDto.getPriority());
            Task task = new Task(taskDto.getDescription(), LocalDateTime.now(), priority);
            taskDao.insert(task);
        } else {
            throw new IllegalArgumentException("Opis zadania nie został podany lub priorytet jest błędny");
        }
    }


    public List<PrintTaskDto> getAllTasks() {
        List<Task> tasks = taskDao.findAll();
        return tasks.stream().map(t -> new PrintTaskDto(t.getId(), t.getDescription(), t.getCreateDate(), t.getPriority().name())).collect(Collectors.toList());
    }

    public void deleteTask(int taskIdToBeDeleted) {
        if (taskIdToBeDeleted > 0) {
            taskDao.deleteById(taskIdToBeDeleted);
        } else {
            throw new IllegalArgumentException("Id zadania jest mniejsze bądź równe 0");
        }
    }

    public void updateTask(String taskDescription, int taskIdToBeUpdated) {
        if (taskDescription != null && taskDescription.length() > 3 && taskIdToBeUpdated > 0) {
            taskDao.update(taskDescription, taskIdToBeUpdated);
        } else {
            throw new IllegalArgumentException("Opis zadania nie został podany lub id zadania jest mniejsze bądź równe 0");
        }
    }
}
