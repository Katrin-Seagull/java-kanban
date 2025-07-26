package test;

import manager.InMemoryHistoryManager;
import model.Status;
import model.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryHistoryManagerTest {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    @Test
    void add() {
        Task task = new Task("Test task", "Description");
        historyManager.addHistory(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "После добавления задачи, история не должна быть пустой.");
        assertEquals(1, history.size(), "После добавления задачи, история не должна быть пустой.");
    }

    @Test
    void testHistoryPreservesPreviousTaskVersions() {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        // Создаем задачу с определенными полями
        Task task1 = new Task("Задача 1", "Описание задачи 1");
        task1.setStatus(Status.NEW);

        // Добавляем задачу в историю
        historyManager.addHistory(task1.copy());

        // Убедимся, что задача сохранена в истории
        List<Task> history = historyManager.getHistory();
        assertNotNull(history);
        assertEquals(1, history.size());
        assertEquals("Задача 1", history.getFirst().getName());
        assertEquals("Описание задачи 1", history.getFirst().getDescription());
        assertEquals(Status.NEW, history.getFirst().getStatus());

        // Изменяем задачу и добавляем её снова
        task1.setName("Обновленная задача 1");
        historyManager.addHistory(task1);

        // Проверяем, что обе версии задачи сохранены
        history = historyManager.getHistory();
        assertEquals(2, history.size());
        assertEquals("Обновленная задача 1", history.get(1).getName()); // Новая версия
        assertEquals("Задача 1", history.get(0).getName());            // Предыдущая версия
    }
}