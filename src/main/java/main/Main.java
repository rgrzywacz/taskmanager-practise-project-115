package main;

import javax.swing.text.LayoutQueue;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.ConnectionManager;
import jakarta.persistence.EntityManager;
import task.PrintTaskDto;
import task.TaskDao;
import task.TaskDto;
import task.TaskService;

public class Main {
    public static void main(String[] args) {

        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.close();

        TaskDao taskDao = new TaskDao();
        TaskService taskService = new TaskService(taskDao);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        while (true) {
            System.out.println("Podaj operacje do wykonania:");
            System.out.println("0 - Zatrzymanie programu");
            System.out.println("1 - Dodaj nowe zadanie");
            System.out.println("2 - Usuń istniejące zadanie");
            System.out.println("3 - Wyświetl wszystkie zadania");
            System.out.println("4 - Aktualizacja zadania");
            Scanner in = new Scanner(System.in);
            int selectedOperation = in.nextInt();
            switch (selectedOperation) {
                case 0 -> System.exit(0);
                case 1 -> {
                    System.out.println("Dodawanie nowego zadania");
                    in.nextLine();
                    System.out.println("Podaj opis zadania: ");
                    String description = in.nextLine();
                    System.out.println("Podaj priorytet zadania - LOW (niski), HIGH (wysoki)");
                    String priority = in.next();
                    TaskDto taskDto = new TaskDto(description, priority);
                    try {
                        taskService.addTask(taskDto);
                        System.out.println("Zadanie zostało dodane");
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("Usuwanie istniejącego zadania");
                    System.out.println("Podaj id zadania, które chcesz usunąć: ");
                    int taskIdToBeDeleted = in.nextInt();
                    taskService.deleteTask(taskIdToBeDeleted);
                }
                case 3 -> {
                    List<PrintTaskDto> tasks = taskService.getAllTasks();
                    // do VM options trzeba dodać "--add-opens java.base/java.time=ALL-UNNAMED"
                    //System.out.println(gson.toJson(tasks));
                    tasks.forEach(System.out::println);
                    System.out.println("Wyświetlanie wszystkich zadań");
                }
                case 4 -> {
                    System.out.println("Aktualizacja istniejącego zadania");
                    System.out.println("Podaj id zadania, które chcesz zaktualizować");
                    int taskIdToBeUpdated = in.nextInt();
                    System.out.println("Podaj nowy opis: ");
                    in.nextLine();
                    String newDescription = in.nextLine();
                    taskService.updateTask(newDescription, taskIdToBeUpdated);
                }
            }
        }
    }
}
