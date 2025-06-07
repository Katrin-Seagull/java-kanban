import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import model.Epic;
import model.SubTask;
import model.Task;
import model.Status;

public class Main {

    public static void main(String[] args) {
        InMemoryTaskManager tm = new InMemoryTaskManager();
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        Task task1 = new Task("Задача 1", "Полить цветы");
        Task task2 = new Task("Задача 2", "Погулять с собакой");
        tm.addTask(task1);
        tm.addTask(task2);

        Epic epic1 = new Epic("Эпик 1", "Посадить дерево");
        tm.addEpic(epic1);
        //int epicId = epic1.getId();
        //System.out.println(epicId);
        SubTask subTask1 = new SubTask("Подзадача 1 для эпика 1", "Раскопать ямку", 2);
        tm.addSubTask(subTask1);
        SubTask subTask2 = new SubTask("Подзадача 2 для эпика 1","Разместить саженец",2);
        tm.addSubTask(subTask2);


        Epic epic2 = new Epic("Эпик 2","Построить дом");
        tm.addEpic(epic2);
        SubTask subTask3 = new SubTask("Подзадача для эпика 2","Поставить купленный дом на участок", 6);
        tm.addSubTask(subTask3);


        System.out.println("Задачи: " + tm.getTasks());
        System.out.println("Подзадачи: " + tm.getSubTasks());
        System.out.println("Эпики: " + tm.getEpics());

        task1.setStatus(Status.DONE);
        subTask1.setStatus(Status.IN_PROGRESS);
        tm.updateEpic(epic1);

        System.out.println("Обновлённые задачи: " + tm.getTasks());
        System.out.println("Обновлённые подзадачи: " + tm.getSubTasks());
        System.out.println("Статус эпика 1: " + epic1.getStatus());

        tm.removeTask(task1.getId());
        tm.removeEpic(epic1.getId());
    }
    public static void printAllTasks(InMemoryTaskManager manager, InMemoryHistoryManager historyManager) {
        System.out.println("Задачи:");
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
        for (Task task : historyManager.getHistory()) {
            System.out.println(task);
        }
    }
}


