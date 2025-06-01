package manager;

import model.Task;
import model.Epic;
import model.SubTask;
import manager.InMemoryTaskManager;
import java.util.List;

public interface TaskManager {
    public static void printAllTasks(InMemoryTaskManager manager) { System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task); }
            System.out.println("Эпики:");
            for (Epic epic : manager.getEpics()) {
                 System.out.println(epic);
                 for (SubTask subTask : manager.getSubTasksForEpic(epic)) {
                      System.out.println("--> " + subTask);
                 }
            }
            System.out.println("Подзадачи:");
            for (SubTask subtask : manager.getSubTasks()) {
                System.out.println(subtask);
            }
            System.out.println("История:");
            for (Task task : manager.getHistory()) {
                System.out.println(task);
            }
    }
}


