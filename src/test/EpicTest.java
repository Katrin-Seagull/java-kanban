package test;
import manager.InMemoryTaskManager;
import model.Epic;
import model.SubTask;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

class EpicTest {
    InMemoryTaskManager taskManager = new InMemoryTaskManager();
    @Test
    void testEpicCannotBeSubtaskOfItself() {
         // Создаем экземпляр менеджера задач
        Epic epic = new Epic("Epic task", "Epic description");
        SubTask subTask = new SubTask("Subtask", "Subtask description", epic.getId());

        try {
            taskManager.addSubTask(subTask); // Добавляем корректную подзадачу
            epic.addSubTaskId(subTask.getId()); // Добавляем идентификатор подзадачи в эпик

            // Пытаемся добавить идентификатор эпика в его собственный список подзадач, что должно вызвать ошибку
            epic.addSubTaskId(epic.getId());
            fail("Эпик был добавлен в качестве подзадачи самому себе.");
        } catch (IllegalArgumentException e) {
            // Ожидаем исключение, если попытка добавления Epica в качестве подзадачи запрещена
        }
    }
}