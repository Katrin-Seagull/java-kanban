package test;

import model.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import manager.InMemoryTaskManager;
import java.util.List;

class TaskTest {
    InMemoryTaskManager taskManager = new InMemoryTaskManager();

    @Test
    void testTasksAreEqualById() {

        // Создаём две задачи с одинаковым описанием и разными id
        Task task1 = new Task("Test task", "Description");
        Task task2 = new Task("Test task", "Description");

        int taskId1 = taskManager.addTask(task1);
        int taskId2 = taskManager.addTask(task2);

        // Получаем задачи из менеджера по их id
        Task savedTask1 = taskManager.getTask(taskId1);
        Task savedTask2 = taskManager.getTask(taskId2);

        // Проверяем, что задачи не равны до установки одинакового id
        assertNotEquals(savedTask1, savedTask2, "Задачи равны до установки одинакового id.");

        // Устанавливаем одинаковый id для проверки равенства
        savedTask1.setId(1);
        savedTask2.setId(1);

        // Проверяем равенство задач после установки одинакового id
        assertEquals(savedTask1, savedTask2, "Задачи не равны при одинаковом id.");
    }
}