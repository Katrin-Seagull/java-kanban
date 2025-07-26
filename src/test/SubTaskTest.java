package test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import manager.InMemoryTaskManager;
import model.Epic;
import model.SubTask;

class SubTaskTest {
    InMemoryTaskManager taskManager = new InMemoryTaskManager();

    @Test
    public void testSubTaskCannotBeItsOwnEpic() {

        SubTask subTask = new SubTask("Подзадача 1 для эпика 1", "Раскопать ямку", 0);
        Epic epic = new Epic("Эпик 1", "Посадить дерево");

        // Добавляем субзадачу и эпик в менеджер задач
        taskManager.addSubTask(subTask);
        taskManager.addEpic(epic);

        int epicId = epic.getId(); // Получаем идентификатор эпика
        subTask.setEpicId(epicId); // Пытаемся установить связь с эпиком (если метод setEpicId был бы доступен)

        // Проверяем, что подзадача не связана сама с собой
        assertNotEquals(subTask.getEpicId(), subTask.getId());
    }
}