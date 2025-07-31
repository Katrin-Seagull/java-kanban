package test;

import manager.InMemoryTaskManager;
import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    InMemoryTaskManager taskManager = new InMemoryTaskManager();

    @Test
    void addNewTask() {
        // Создаем экземпляр менеджера задач

        Task task = new Task("Test addNewTask", "Test addNewTask description");
        final int taskId = taskManager.addTask(task); // Используем экземпляр для добавления задачи

        Task savedTask = taskManager.getTask(taskId); // Используем экземпляр для получения задачи

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks(); // Используем экземпляр для получения списка задач

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.getFirst(), "Задачи не совпадают.");
    }

    @Test
    void testEqualsById() { //для каждой задачи и подзадачи генерируется уникальный ID, поэтому их ID не будут совпадать.
        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        // Создаем задачу и добавляем ее в менеджер
        Task task = new Task("Задача 1", "Описание задачи 1");
        int taskId = taskManager.addTask(task);

        Epic epic1 = new Epic("Название эпика 1", "Описание эпика 1");
        int epicId = taskManager.addTask(epic1);

        // Создаем подзадачу с таким же ID
        SubTask subTask = new SubTask("Подзадача 1", "Описание подзадачи 1", epicId);
        int subTaskId = taskManager.addTask(subTask);

        // Убедимся, что задача и подзадача равны по ID
        assertNotEquals(taskId, subTaskId);
    }

    @Test
    void testAddingAndFindingTasks() {
        // Создаем задачи разных типов
        Task task = new Task("Задача", "Описание задачи");
        SubTask subTask = new SubTask("Подзадача", "Описание подзадачи", 0);
        Epic epic = new Epic("Эпик", "Описание эпика");
        // Добавляем задачи в менеджер
        int taskId = taskManager.addTask(task);
        taskManager.addSubTask(subTask);
        taskManager.addEpic(epic);
        // Проверяем, что задачи были добавлены
        assertNotNull(taskManager.getTask(taskId), "Задача должна быть найдена по ID.");
        assertNotNull(taskManager.getSubTask(subTask.getId()), "Подзадача должна быть найдена по ID.");
        assertNotNull(taskManager.getEpic(epic.getId()), "Эпик должен быть найден по ID.");
    }

    @Test
    void testIdConflict() {
        // Создаем задачи без явно заданных ID
        Task task = new Task("Задача с автоматически сгенерированным ID", "Описание задачи");
        SubTask subTask = new SubTask("Подзадача с автоматически сгенерированным ID", "Описание подзадачи", 0);
        Epic epic = new Epic("Эпик с автоматически сгенерированным ID", "Описание эпика");

        // Добавляем задачи и получаем сгенерированные ID
        int taskId = taskManager.addTask(task);
        taskManager.addSubTask(subTask);
        taskManager.addEpic(epic);

        // Проверяем, что задачи доступны по их ID
        assertNotNull(taskManager.getTask(taskId), "Задача с автоматически сгенерированным ID должна быть найдена.");
        assertNotNull(taskManager.getSubTask(subTask.getId()), "Подзадача с автоматически сгенерированным ID должна быть найдена.");
        assertNotNull(taskManager.getEpic(epic.getId()), "Эпик с автоматически сгенерированным ID должен быть найден.");
    }

    @Test
    void testTaskIntegrityOnAdd() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        // Создаем задачу с определенными полями
        Task originalTask = new Task("Исходная задача", "Описание исходной задачи");
        originalTask.setStatus(Status.NEW);

        // Добавляем задачу в менеджер
        int addedTaskId = taskManager.addTask(originalTask);

        // Получаем задачу из менеджера
        Task retrievedTask = taskManager.getTask(addedTaskId);

        // Проверяем, что все поля задачи остались неизменными
        assertEquals(originalTask.getName(), retrievedTask.getName());
        assertEquals(originalTask.getDescription(), retrievedTask.getDescription());
        assertEquals(originalTask.getStatus(), retrievedTask.getStatus());
    }
}