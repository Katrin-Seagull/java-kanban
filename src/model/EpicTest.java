package model;

import manager.InMemoryTaskManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import manager.TaskManager;

class EpicTest {

    /*Test
    void testEpicCannotBeSubtaskOfItself() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager(); // Создаем экземпляр менеджера задач
        Epic epic = new Epic("Epic task", "Epic description");
        SubTask subTask = new SubTask("Subtask", "Subtask description", epic.getId()); // Создаем подзадачу

        try {
            taskManager.addSubTask(epic); // Пытаемся добавить Epic как подзадачу, что должно вызвать ошибку
            fail("Эпик был добавлен в качестве подзадачи.");
        } catch (IllegalArgumentException e) {
            // Ожидаем исключение, если попытка добавления Epica в качестве подзадачи запрещена
        }
    }

      */
}