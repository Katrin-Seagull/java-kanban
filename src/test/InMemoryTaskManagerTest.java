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
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }
    @Test
    void testEqualsById() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        // Создаем задачу и добавляем ее в менеджер
        Task task = new Task("Задача 1", "Описание задачи 1");
        int taskId = taskManager.addTask(task);

        // Создаем подзадачу с таким же ID
        SubTask subTask = new SubTask("Подзадача 1", "Описание подзадачи 1", taskId);
        subTask.setId(taskId);

        // Убедимся, что задача и подзадача равны по ID
        assertEquals(taskManager.getTask(taskId), taskManager.getSubTask(taskId));
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

        // Создаем задачи с явно заданным ID
        Task taskWithId = new Task("Задача с ID", "Описание задачи");
        taskWithId.setId(100);
        SubTask subTaskWithId = new SubTask("Подзадача с ID", "Описание подзадачи", 0);
        subTaskWithId.setId(200);
        Epic epicWithId = new Epic("Эпик с ID", "Описание эпика");
        epicWithId.setId(300);

        // Добавляем задачи с явно заданными ID
        taskManager.addTask(taskWithId);
        taskManager.addSubTask(subTaskWithId);
        taskManager.addEpic(epicWithId);

        // Создаем и добавляем задачи с автоматически сгенерированными ID
        Task autoTask = new Task("Авто задача", "Описание авто задачи");
        int autoTaskId = taskManager.addTask(autoTask);
        SubTask autoSubTask = new SubTask("Авто подзадача", "Описание авто подзадачи", 0);
        taskManager.addSubTask(autoSubTask);
        Epic autoEpic = new Epic("Авто эпик", "Описание авто эпика");
        taskManager.addEpic(autoEpic);

        // Проверяем, что задачи доступны по их ID
        assertNotNull(taskManager.getTask(100), "Задача с явно заданным ID должна быть найдена.");
        assertNotNull(taskManager.getSubTask(200), "Подзадача с явно заданным ID должна быть найдена.");
        assertNotNull(taskManager.getEpic(300), "Эпик с явно заданным ID должен быть найден.");

        // Проверяем, что автоматически сгенерированные ID не совпадают с явно заданными
        assertNotEquals(autoTaskId, 100);
        assertNotEquals(autoTaskId, 200);
        assertNotEquals(autoTaskId, 300);
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