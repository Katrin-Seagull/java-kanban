package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import manager.InMemoryTaskManager;
import model.Epic;
import model.SubTask;

class SubTaskTest {
    InMemoryTaskManager taskManager = new InMemoryTaskManager();

    @Test
    void testSubTasksEqualityByID() {
        // Создаем эпик для подзадач
        Epic epic = new Epic("Эпик для подзадач", "Описание эпика");
        taskManager.addEpic(epic);

        // Создаем две подзадачи с одинаковым ID
        SubTask subTask1 = new SubTask("Подзадача 1", "Описание подзадачи 1", epic.getId());
        SubTask subTask2 = new SubTask("Подзадача 2", "Описание подзадачи 2", epic.getId());

        // Добавляем подзадачи в менеджер задач
        taskManager.addSubTask(subTask1);
        taskManager.addSubTask(subTask2);
        subTask1.setId(1);
        subTask2.setId(1);
        // Проверяем равенство подзадач по ID
        assertEquals(subTask1.getId(), subTask2.getId());
    }
}